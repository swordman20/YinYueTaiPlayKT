package com.example.yinyuetaiplaykt.ui.activity

import androidx.appcompat.widget.Toolbar
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.base.BaseActivity
import com.example.yinyuetaiplaykt.util.FragmentUtil
import com.example.yinyuetaiplaykt.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), ToolBarManager {

    override fun getLayoutId(): Int = R.layout.activity_main

    //惰性加载，不用则不加载
    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    override fun initData() {
        super.initData()
        initMainToolBar()
    }

    override fun initListener() {
        super.initListener()
        //tab切换监听
        bottomBar.setOnTabSelectListener {
            val fragment = FragmentUtil.instance.getFragmentById(it)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment!!, it.toString())
            transaction.commit()
        }
    }
}