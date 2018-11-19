package com.zjwam.kotlintest.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.github.jdsjlzx.interfaces.OnLoadMoreListener
import com.github.jdsjlzx.interfaces.OnRefreshListener
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.github.jdsjlzx.recyclerview.ProgressStyle
import com.zjwam.kotlintest.BaseActivity
import com.zjwam.kotlintest.R
import com.zjwam.kotlintest.adapter.JobRecommendAdapter
import com.zjwam.kotlintest.bean.JobHomeBean
import com.zjwam.kotlintest.presenter.IPresenter
import com.zjwam.kotlintest.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IView {
    var jobRecommendAdapter: JobRecommendAdapter? = null
    var lRecyclerViewAdapter: LRecyclerViewAdapter? = null
    var mCurrentCounter: Int = 0
    var max_items: Int = 0
    var page: Int = 0
    var isRefresh: Boolean = false
    var jobHomePresenter: IPresenter? = null
    override fun refresh() {
        jobRecommendAdapter!!.clear()
    }

    override fun getData(list: List<JobHomeBean.Resume>, count: Int) {
        max_items = count
        jobRecommendAdapter!!.addAll(list)
        mCurrentCounter += list.size
    }

    override fun refreshComplele() {
        recyclerview.refreshComplete(10)
        lRecyclerViewAdapter!!.notifyDataSetChanged()
    }

    override fun showMsg(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        collapsingToolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(
                baseContext,
                R.color.white
            )
        )
        collapsingToolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                baseContext,
                R.color.black
            )
        )
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.back)
    }

    private fun initData() {
        toolbar.setNavigationOnClickListener({ view -> finish() })
        jobHomePresenter = Presenter(this, this)
        jobRecommendAdapter = JobRecommendAdapter(this)
        lRecyclerViewAdapter = LRecyclerViewAdapter(jobRecommendAdapter)
        recyclerview.setAdapter(lRecyclerViewAdapter)
        recyclerview.setLayoutManager(LinearLayoutManager(baseContext))
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader)
        recyclerview.setFooterViewColor(R.color.colorAccent, R.color.black, android.R.color.white)
        recyclerview.setFooterViewHint("拼命加载中", "-----我是有底线的-----", "网络不给力啊，点击再试一次吧")
        recyclerview.setOnRefreshListener({
            isRefresh = true
            page = 1
            mCurrentCounter = 0
            (jobHomePresenter as Presenter).getData(page.toString(), isRefresh)
        })
        recyclerview.setOnLoadMoreListener( {
            isRefresh = false
            if (mCurrentCounter < max_items) {
                page++
                (jobHomePresenter as Presenter).getData(page.toString(), isRefresh)
            } else {
                recyclerview.setNoMore(true)
            }
        })
        recyclerview.refresh()
    }
}
