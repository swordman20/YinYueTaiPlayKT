package com.example.yinyuetaiplaykt.presenter.impl

import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.presenter.interf.HomePresenter
import com.example.yinyuetaiplaykt.util.ThreadUtil
import com.example.yinyuetaiplaykt.util.URLProviderUtils
import com.example.yinyuetaiplaykt.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
//这里的var修饰是为了该class里面的所有方法都可以使用homeView，如果不添加var，只有init（构造方法）能使用
class HomePresenterImpl(var homeView: HomeView) : HomePresenter {
    override fun loadDatas() {

        val path = URLProviderUtils.getHomeUrl(1, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            //在子线程
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    homeView.onError(e.message)
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val gson = Gson()
                //这一点和Java使用有一点不同
                val listBean =
                    gson.fromJson<BaseListBean>(result, object : TypeToken<BaseListBean>() {}.type)

                ThreadUtil.runOnMainThread(Runnable {
                    homeView.onLoadSuccess(listBean.list)
                })
            }

        })

    }

    override fun loadMore(pageNum: Int) {

        val path = URLProviderUtils.getHomeUrl(pageNum, 20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            //在子线程
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    homeView.onError(e.message)
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()

                val gson = Gson()
                //这一点和Java使用有一点不同
                val listBean =
                    gson.fromJson<BaseListBean>(result, object : TypeToken<BaseListBean>() {}.type)

                ThreadUtil.runOnMainThread(Runnable {

                    homeView.onMoreSuccess(listBean.list)
                })
            }

        })

    }

}