package com.example.yinyuetaiplaykt.ui.fragment

import android.view.View
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.adapter.TabPageAdapter
import com.example.yinyuetaiplaykt.base.BaseFragment
import com.example.yinyuetaiplaykt.model.TabBean
import com.example.yinyuetaiplaykt.util.StringUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_mv.*
import kotlin.reflect.typeOf

class MvFragment : BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_mv,null)
    }

    override fun initData() {
        val assetsGetJson = StringUtil.AssetsGetJson("json/tab.json", context)
        val fromJson = Gson().fromJson<List<TabBean>>(assetsGetJson,
            object : TypeToken<List<TabBean>>() {}.type)
        val tabPageAdapter = TabPageAdapter(fromJson, childFragmentManager)
        viewPager.adapter = tabPageAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}