package com.zjwam.kotlintest.model

import android.content.Context
import com.lzy.okgo.model.Response
import com.zjwam.kotlintest.bean.JobHomeBean
import com.zjwam.kotlintest.bean.ResponseBean
import com.zjwam.kotlintest.callback.BasicCallback
import com.zjwam.kotlintest.callback.JsonCallback
import com.zjwam.kotlintest.utils.OkGoUtils

class Model : IModel{
    override fun getData(
        url: String,
        context: Context?,
        param: Map<String, String>,
        basicCallback: BasicCallback<ResponseBean<JobHomeBean>>
    ) {
        var jsonCallback = object : JsonCallback<ResponseBean<JobHomeBean>>() {
            override fun onSuccess(response: Response<ResponseBean<JobHomeBean>>) {
                basicCallback.onSuccess(response)
            }

            override fun onError(response: Response<ResponseBean<JobHomeBean>>) {
                super.onError(response)
                basicCallback.onError(response)
            }

            override fun onFinish() {
                super.onFinish()
                basicCallback.onFinish()
            }
        }
        OkGoUtils.postRequets(url, context, param, jsonCallback)
    }
}
