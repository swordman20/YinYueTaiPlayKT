package com.example.yinyuetaiplaykt.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.adapter.HomeAdapter
import com.example.yinyuetaiplaykt.base.BaseFragment
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.presenter.impl.HomePresenterImpl
import com.example.yinyuetaiplaykt.util.ThreadUtil
import com.example.yinyuetaiplaykt.util.URLProviderUtils
import com.example.yinyuetaiplaykt.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomeFragment : BaseFragment(), HomeView {
    private var pageNum = 1
    private val homeAdapter by lazy { HomeAdapter() }
    private val mPresenter by lazy { HomePresenterImpl(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.destroyView()
    }

    override fun initListener() {
        super.initListener()
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = homeAdapter
        //swipeRefreshLayout
        //varagr 可变参数的意思
        swipeRefreshLayout.setColorSchemeColors(Color.YELLOW,Color.RED,Color.BLUE)
        swipeRefreshLayout.setOnRefreshListener {
//            loadDatas()
            pageNum = 1
            mPresenter.loadDatas(pageNum)
        }
        recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when(newState){
                    //如果滑动停止，并且是LinearLayoutManager的最后一条
                    RecyclerView.SCROLL_STATE_IDLE->{
//                        myLog { "滑动停止了" }
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is LinearLayoutManager) {
                            val lastPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                            if (lastPosition == homeAdapter.itemCount-1) {
                                //最后一条已经显示，请求网络加载更多数据
                                var positiono=homeAdapter.itemCount-1
                                myLog { "最后一条已经显示，请求网络加载更多数据$positiono" }
                                ++pageNum
//                                loadMore(pageNum)
                                mPresenter.loadMore(pageNum)
                            }
                        }
                    }
                }
            }
        })

    }

    override fun initData() {
            pageNum = 1
            mPresenter.loadDatas(pageNum)
//        loadDatas()
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
                ThreadUtil.runOnMainThread(Runnable {
                    swipeRefreshLayout.isRefreshing = false
                })
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
                    swipeRefreshLayout.isRefreshing = false
                    homeAdapter.updateList(listBean.list)
                })
            }

        })
    }
    private fun loadMore(pageNum:Int) {
        val path = URLProviderUtils.getHomeUrl(pageNum, 20)
        myLog { "请求URL$path" }
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            //在子线程
            override fun onFailure(call: Call, e: IOException) {
                myToast("获取数据失败")
                ThreadUtil.runOnMainThread(Runnable {
                    swipeRefreshLayout.isRefreshing = false
                })
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
                    swipeRefreshLayout.isRefreshing = false
                    homeAdapter.addMoreList(listBean.list)
                })
            }

        })
    }

    override fun onMoreSuccess(list: List<HomeItemBean>?) {
        swipeRefreshLayout.isRefreshing = false
        homeAdapter.addMoreList(list)
    }

    override fun onError(message: String?) {
        swipeRefreshLayout.isRefreshing = false
        myToast(message)
    }

    override fun onLoadSuccess(list: List<HomeItemBean>?) {
        swipeRefreshLayout.isRefreshing = false
        homeAdapter.updateList(list)
    }
}