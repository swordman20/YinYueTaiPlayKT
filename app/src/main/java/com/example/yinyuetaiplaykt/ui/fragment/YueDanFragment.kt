package com.example.yinyuetaiplaykt.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.adapter.YueDanAdapter
import com.example.yinyuetaiplaykt.base.BaseFragment
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.presenter.impl.YueDanPresenterImpl
import com.example.yinyuetaiplaykt.view.YueDanView
import kotlinx.android.synthetic.main.fragment_yuedan.*

class YueDanFragment : BaseFragment(), YueDanView {
    private val adapter by lazy { YueDanAdapter() }
    private val mPresenter by lazy { YueDanPresenterImpl(this) }
    private var pageNum = 1
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_yuedan, null)
    }

    override fun initListener() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener {
            pageNum = 1
            mPresenter.loadDatas(pageNum)
        }
        mPresenter.loadDatas(pageNum)

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    //如果滑动到最后一条
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val layoutManager = recyclerView.layoutManager
                        if (layoutManager is LinearLayoutManager) {
                            val lastPosition =
                                layoutManager.findLastCompletelyVisibleItemPosition()
                            if (lastPosition == adapter.itemCount - 1) {
                                ++pageNum
                                mPresenter.loadMore(pageNum)
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onMoreSuccess(list: List<HomeItemBean>?) {
        adapter.moreData(list)
    }

    override fun onError(message: String?) {
        swipeRefreshLayout.isRefreshing = false
        myToast(message)
    }

    override fun onLoadSuccess(list: List<HomeItemBean>?) {
        swipeRefreshLayout.isRefreshing = false
        adapter.initData(list)
    }

    override fun onDestroyOptionsMenu() {
        mPresenter.destroyView()
    }
}