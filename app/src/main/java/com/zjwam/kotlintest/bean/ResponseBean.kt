package com.zjwam.kotlintest.bean

import java.io.Serializable

class ResponseBean<T> : Serializable{
    var code: Int = 0
    var msg: String? = null
    var data: T? = null

     override fun toString(): String {
        return "{\"code\":$code,\"msg\":\"$msg\"}"
    }
}