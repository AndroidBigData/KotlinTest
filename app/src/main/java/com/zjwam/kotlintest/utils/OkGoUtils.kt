package com.zjwam.kotlintest.utils

import android.content.Context
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.zjwam.kotlintest.callback.JsonCallback

object OkGoUtils {
    /**
     * 不带缓存的网络请求
     * @param url
     * @param tag
     * @param callback
     * @param <T>
     */

    /**
     * 固定数据格式：
     * code
     * msg
     * daga
     */
    fun <T> getRequets(url: String, tag: Any, callback: JsonCallback<T>) {
        OkGo.get<T>(url)
            .tag(tag)
            .cacheMode(CacheMode.NO_CACHE)
            .execute(callback)
    }

   fun <T> postRequets(url: String, tag: Context?, params: Map<String, String>, callback: JsonCallback<T>) {
        OkGo.post<T>(url)
            .tag(tag)
            .params(params)
            .cacheMode(CacheMode.NO_CACHE)
            .execute(callback)
    }
}