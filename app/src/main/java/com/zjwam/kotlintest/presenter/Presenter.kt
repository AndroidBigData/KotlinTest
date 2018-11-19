package com.zjwam.kotlintest.presenter

import android.content.Context
import com.lzy.okgo.model.Response
import com.zjwam.kotlintest.bean.JobHomeBean
import com.zjwam.kotlintest.bean.ResponseBean
import com.zjwam.kotlintest.callback.BasicCallback
import com.zjwam.kotlintest.model.IModel
import com.zjwam.kotlintest.model.Model
import com.zjwam.kotlintest.utils.HttpErrorMsg
import com.zjwam.kotlintest.view.IView
import java.util.HashMap

class Presenter: IPresenter {
    var iView:IView?= null
    var iModel:IModel?= null
    var context:Context?=null
    var param:Map<String,String>?=null
    constructor(context: Context, iView:IView){
        this.context = context
        this.iView = iView
        iModel = Model()
    }

    override fun getData(page:String,isRefresh:Boolean) {
        param = HashMap()
        (param as HashMap<String, String>).put("page", page)
        (param as HashMap<String, String>).put("city", "郑州")
        iModel!!.getData("http://zkw.org.cn/api/resume/index", context, param as HashMap<String, String>, object : BasicCallback<ResponseBean<JobHomeBean>> {
                override fun onSuccess(response: Response<ResponseBean<JobHomeBean>>) {
                    if (isRefresh) {
                        iView!!.refresh()
                    }
                    val jobHomeBean = response.body().data
                    iView!!.getData(jobHomeBean!!.getResume()!!, jobHomeBean!!.getCount())
                }

                override fun onError(response: Response<ResponseBean<JobHomeBean>>) {
                    val exception = response.getException()
                    val error = HttpErrorMsg.getErrorMsg(exception)
                    iView!!.showMsg(error)
                }

                override fun onFinish() {
                    iView!!.refreshComplele()
                }
            })
    }
}