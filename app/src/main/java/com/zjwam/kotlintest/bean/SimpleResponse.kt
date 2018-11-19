package com.zjwam.kotlintest.bean

import java.io.Serializable

class SimpleResponse<T> : Serializable{
     var code: Int = 0
     var msg: String? = null
     fun toLzyResponse(): ResponseBean<T> {
        val responseBean = ResponseBean<T>()
        responseBean.code = code
        responseBean.msg = msg
        return responseBean
    }
}