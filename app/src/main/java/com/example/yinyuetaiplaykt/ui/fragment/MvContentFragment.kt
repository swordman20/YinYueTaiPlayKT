package com.example.yinyuetaiplaykt.ui.fragment

import android.view.View
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mv_content.*

/**
 *    author : hades
 *    date   : 2021/1/18
 *    desc   :
 */
class MvContentFragment :BaseFragment() {
    var pageName : String = ""
    override fun init() {
        super.init()
        pageName = arguments?.getString("args")!!
    }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_mv_content, null)
    }

    override fun initListener() {
        tv_c.text = pageName
    }
}