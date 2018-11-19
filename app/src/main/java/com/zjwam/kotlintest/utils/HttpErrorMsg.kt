package com.zjwam.kotlintest.utils

import com.google.gson.JsonSyntaxException
import com.lzy.okgo.exception.HttpException
import com.lzy.okgo.exception.StorageException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HttpErrorMsg {
    companion object {
        fun getErrorMsg(exception: Throwable?): String {
            exception?.printStackTrace()
            if (exception is UnknownHostException || exception is ConnectException) {
                return "网络连接失败，请连接网络"
            } else if (exception is SocketTimeoutException) {
                return "网络请求超时"
            } else if (exception is HttpException) {
                return "服务端响应码404和500"
            } else if (exception is StorageException) {
                println("SD卡不存在或者没有权限")
                return "SD卡不存在或者没有权限"
            } else  if (exception is MyException) {
                return exception.getErrorBean().msg.toString()
            } else if (exception is JsonSyntaxException) {
                return "数据解析错误"
            } else {
                return exception!!.toString()
            }
        }
    }

}