package com.example.yinyuetaiplaykt.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yinyuetaiplaykt.BuildConfig

/**
 *    author : hades
 *    date   : 2021/1/2
 *    desc   :所有acitivity的基类
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }

    /**
     * 初始化数据操作
     */
    protected open fun initData() {

    }

    /**
     * 获取布局id
     * 由子类复写
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化apapter、listener
     */
    protected open fun initListener() {}

    protected fun myToast(str:String?){
        runOnUiThread { Toast.makeText(this,str,Toast.LENGTH_SHORT).show() }
    }

    inline fun myLog(lazyMessage: () -> Any?) {
        if (BuildConfig.DEBUG) {
            Log.d(localClassName, lazyMessage()?.toString()?:"null")
        }
    }

    inline fun <reified T: BaseActivity> Context.startActivityAndFinish(vararg params:Pair<String,Any>){
        val intent = Intent(this, T::class.java)
        startActivity(intent)
        finish()
    }
}