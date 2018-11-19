package com.zjwam.kotlintest.model

import android.content.Context
import com.zjwam.kotlintest.bean.JobHomeBean
import com.zjwam.kotlintest.bean.ResponseBean
import com.zjwam.kotlintest.callback.BasicCallback

interface IModel {
    fun getData(url: String, context: Context?, param: Map<String, String>, basicCallback: BasicCallback<ResponseBean<JobHomeBean>>)
}