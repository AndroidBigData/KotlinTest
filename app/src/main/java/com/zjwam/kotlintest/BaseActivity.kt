package com.zjwam.kotlintest

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jaeger.library.StatusBarUtil

open class BaseActivity : AppCompatActivity() {
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setStatusBar()
    }

    private fun setStatusBar() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(baseContext,R.color.colorPrimary), 60)
    }

    fun error(msg:String){
        Toast.makeText(baseContext,msg,Toast.LENGTH_SHORT).show()
    }
}