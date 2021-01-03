package com.example.yinyuetaiplaykt.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.adapter.HomeAdapter
import com.example.yinyuetaiplaykt.base.BaseFragment
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.util.ThreadUtil
import com.example.yinyuetaiplaykt.util.URLProviderUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomeFragment : BaseFragment() {
    private val homeAdapter by lazy { HomeAdapter() }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun initListener() {
        super.initListener()
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = homeAdapter
    }

    override fun initData() {
        loadDatas()
    }

    private fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(1, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            //在子线程
            override fun onFailure(call: Call, e: IOException) {
                myToast("获取数据失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                myLog { "获取数据成功${result}" }
                val gson = Gson()
                //这一点和Java使用有一点不同
                val listBean =
                    gson.fromJson<BaseListBean>(result, object : TypeToken<BaseListBean>() {}.type)
                myLog { "数据长度${listBean.list.size}" }

                ThreadUtil.runOnMainThread(Runnable {
                    homeAdapter.updateList(listBean.list)
                })
            }

        })
    }
}