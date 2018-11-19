package com.zjwam.kotlintest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zjwam.kotlintest.R
import com.zjwam.kotlintest.bean.JobHomeBean
import com.zjwam.kotlintest.utils.FlowLayout

class JobRecommendAdapter(context: Context) : ListBaseAdapter<JobHomeBean.Resume>() {
    private val mLayoutInflater: LayoutInflater
    private var viewHolder: ViewHolder? = null
    private var item: JobHomeBean.Resume? = null

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mContext = context
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(mLayoutInflater.inflate(R.layout.job_home_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        item = mDataList[position]
        viewHolder = holder as ViewHolder
        viewHolder!!.job_item_title.text = item!!.job_name
        viewHolder!!.job_item_money.text = item!!.salary
        viewHolder!!.job_item_company.text = item!!.company_name
        viewHolder!!.job_item_time.text = item!!.create_time
        viewHolder!!.job_item_position.text = item!!.area
        viewHolder!!.job_item_type.text = item!!.type
        initBenefit()
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val job_item_title: TextView
        val job_item_money: TextView
        val job_item_company: TextView
        val job_item_time: TextView
        val job_item_type: TextView
        val job_item_position: TextView
        val job_item_benefit: FlowLayout

        init {
            job_item_title = itemView.findViewById(R.id.job_item_title)
            job_item_money = itemView.findViewById(R.id.job_item_money)
            job_item_company = itemView.findViewById(R.id.job_item_company)
            job_item_time = itemView.findViewById(R.id.job_item_time)
            job_item_type = itemView.findViewById(R.id.job_item_type)
            job_item_position = itemView.findViewById(R.id.job_item_position)
            job_item_benefit = itemView.findViewById(R.id.job_item_benefit)
        }
    }

    private fun initBenefit() {
        viewHolder!!.job_item_benefit.removeAllViews()
        for (i in 0 until item!!.benefit!!.size) {
            val option = item!!.benefit!![i]
            val checkboxView = LayoutInflater.from(mContext).inflate(
                R.layout.job_benefit,
                viewHolder!!.job_item_benefit,
                false
            ) as TextView
            checkboxView.text = "  " + option.type + "  "
            viewHolder!!.job_item_benefit.addView(checkboxView)
        }
    }
}
