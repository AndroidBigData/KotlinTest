package com.zjwam.kotlintest.callback

import com.lzy.okgo.model.Response

interface BasicCallback<T> {
    fun onSuccess(response: Response<T>)
    fun onError(response: Response<T>)
    fun onFinish()
}