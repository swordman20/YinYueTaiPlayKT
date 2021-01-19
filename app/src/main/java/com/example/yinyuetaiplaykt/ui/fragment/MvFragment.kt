package com.example.yinyuetaiplaykt.ui.fragment

import android.view.View
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.adapter.MvTabAdapter
import com.example.yinyuetaiplaykt.base.BaseFragment
import com.example.yinyuetaiplaykt.model.MVTabBean
import com.example.yinyuetaiplaykt.util.StringUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_mv.*

class MvFragment : BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_mv,null)
    }

    override fun initData() {
        val assetsJsonRead = StringUtil.assetsJsonRead("tab.json", context)
        val fromJson = Gson().fromJson<List<MVTabBean>>(assetsJsonRead,
            object : TypeToken<List<MVTabBean>>() {}.type)
        //在Fragment中管理Fragment要用childFragmentManager
        viewPager.adapter = MvTabAdapter(fromJson,childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}