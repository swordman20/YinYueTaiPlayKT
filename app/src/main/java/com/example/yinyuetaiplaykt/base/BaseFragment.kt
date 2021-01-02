package com.example.yinyuetaiplaykt.base

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yinyuetaiplaykt.BuildConfig

/**
 *    author : hades
 *    date   : 2021/1/2
 *    desc   :所有Fragment的基类
 */
abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    protected open fun init() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initView()
    }

    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    /**
     * 数据初始化
     */
    protected open fun initData() {
    }

    /**
     * listener
     * adapter
     */
    protected open fun initListener() {
    }

    protected fun myToast(str:String?){
        (context as Activity).runOnUiThread { Toast.makeText(context,str,Toast.LENGTH_SHORT).show() }
    }
    inline fun myLog(lazyMessage: () -> Any?) {
        if (BuildConfig.DEBUG) {
            Log.d(this.tag, lazyMessage()?.toString()?:"null")
        }
    }

}