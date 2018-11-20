package com.zjwam.kotlintest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.util.ArrayList


abstract class ListBaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var mContext: Context? = null
    protected var mScreenWidth: Int = 0

    fun setScreenWidth(width: Int) {
        mScreenWidth = width
    }

    protected var mDataList = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return null!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getDataList(): List<T> {
        return mDataList
    }

    fun setDataList(list: Collection<T>) {
        this.mDataList.clear()
        this.mDataList.addAll(list)
        notifyDataSetChanged()
    }

    fun addAll(list: Collection<T>) {
        val lastIndex = this.mDataList.size
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size)
        }
    }

    fun clear() {
        mDataList.clear()
        notifyDataSetChanged()
    }
}