package com.zjwam.kotlintest.view

import com.zjwam.kotlintest.bean.JobHomeBean

interface IView {
    fun showMsg(msg:String)
    fun refresh()
    fun getData(list:List<JobHomeBean.Resume> , count:Int)
    fun refreshComplele()
}