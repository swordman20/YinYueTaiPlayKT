package com.example.yinyuetaiplaykt.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.model.HomeItemBean
import kotlinx.android.synthetic.main.fragment_list.*

abstract class BaseListFragment<RESPONSE,ITEMBEAN,ITEMVIEW:View> : BaseFragment(), BaseView<RESPONSE> {
    private val adapter by lazy { createAdapter() }

    abstract fun createAdapter(): BaseListAdapter<ITEMBEAN,ITEMVIEW>

    private val mPresenter by lazy { createPresenter() }

    abstract fun createPresenter():BasePresenter

    private var pageNum = 1
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener {
            pageNum = 1
            mPresenter.loadData(pageNum)
        }
        mPresenter.loadData(pageNum)

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

    abstract fun getItemData(response: RESPONSE): List<ITEMBEAN>

    override fun onError(msg: String?) {
        swipeRefreshLayout.isRefreshing = false
        msg?.let {
            myToast(it)
        }
    }

    override fun onLoadSuccess(response: RESPONSE) {
        swipeRefreshLayout.isRefreshing = false
        adapter.setData(getItemData(response))
    }
    override fun onMoreSuccess(response: RESPONSE) {
        adapter.addData(getItemData(response))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.onDetachView()
    }

}