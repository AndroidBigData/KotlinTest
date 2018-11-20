package com.zjwam.kotlintest.presenter

import android.content.Context
import com.lzy.okgo.model.Response
import com.zjwam.kotlintest.bean.JobHomeBean
import com.zjwam.kotlintest.bean.ResponseBean
import com.zjwam.kotlintest.callback.BasicCallback
import com.zjwam.kotlintest.model.IModel
import com.zjwam.kotlintest.model.Model
import com.zjwam.kotlintest.utils.HttpErrorMsg
import com.zjwam.kotlintest.utils.UrlUtils
import com.zjwam.kotlintest.view.IView

class Presenter(context: Context, iView:IView): IPresenter {
    var iView:IView?= null
    var iModel:IModel?= null
    var context:Context?=null
    var param:Map<String,String>?=null

    init{
        this.context = context
        this.iView = iView
        iModel = Model()
    }

    override fun getData(page:String,isRefresh:Boolean) {
        param = hashMapOf("page" to page,"city" to "郑州")
        iModel!!.getData(UrlUtils.jobList, context, param!!, object : BasicCallback<ResponseBean<JobHomeBean>> {
                override fun onSuccess(response: Response<ResponseBean<JobHomeBean>>) {
                    if (isRefresh) {
                        iView!!.refresh()
                    }
                    val jobHomeBean = response.body().data
                    iView!!.getData(jobHomeBean!!.getResume()!!, jobHomeBean.getCount())
                }

                override fun onError(response: Response<ResponseBean<JobHomeBean>>) {
                    val exception = response.exception
                    val error = HttpErrorMsg.getErrorMsg(exception)
                    iView!!.showMsg(error)
                }

                override fun onFinish() {
                    iView!!.refreshComplele()
                }
            })
    }
}