package com.zjwam.kotlintest.utils

import com.google.gson.Gson
import com.zjwam.kotlintest.bean.SimpleResponse

class MyException : IllegalStateException {
    private var errorBean: SimpleResponse<IllegalStateException>

    constructor(toString: String){
        errorBean = Gson().fromJson<SimpleResponse<IllegalStateException>>(toString, SimpleResponse::class.java!!)
    }

    fun getErrorBean(): SimpleResponse<IllegalStateException> {
        return errorBean
    }
}