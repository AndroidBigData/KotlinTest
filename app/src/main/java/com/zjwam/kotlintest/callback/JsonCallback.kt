package com.zjwam.kotlintest.callback

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.lzy.okgo.callback.AbsCallback
import com.zjwam.kotlintest.bean.ResponseBean
import com.zjwam.kotlintest.bean.SimpleResponse
import com.zjwam.kotlintest.utils.MyException
import okhttp3.Response
import java.lang.reflect.ParameterizedType

abstract class JsonCallback<T> : AbsCallback<T>() {

    override fun convertResponse(response: Response): T? {

        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback

        val genType = javaClass.genericSuperclass
        val params = (genType as ParameterizedType).actualTypeArguments
        val type = params[0] as? ParameterizedType ?: throw IllegalStateException("没有填写泛型!")
        val rawType = type.rawType
        val typeArgument = type.actualTypeArguments[0]
        val body = response.body() ?: return null
        val gson = Gson()
        val jsonReader = JsonReader(body.charStream())
        if (rawType !== ResponseBean::class.java) {
            val data = gson.fromJson<T>(jsonReader, type)
            response.close()
            return data
        } else {
            if (typeArgument === Void::class.java) {
                val simpleResponse = gson.fromJson<SimpleResponse<T>>(jsonReader, SimpleResponse::class.java)
                response.close()
                return simpleResponse.toLzyResponse() as T
            } else {
                val responseBean : ResponseBean<T> = gson.fromJson(jsonReader, type)
                response.close()
                val code = responseBean.code
                //与服务器约定的各种code
                return if (code == 1) {
                    responseBean as T
                } else {
                    throw MyException(responseBean.toString())
                }
            }
        }
    }
}