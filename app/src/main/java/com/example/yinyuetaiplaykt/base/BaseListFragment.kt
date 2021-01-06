package com.example.yinyuetaiplaykt.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.R
import kotlinx.android.synthetic.main.fragment_yuedan.*

/**
 *    author : hades
 *    date   : 2021/1/6
 *    desc   :
 *    BaseView
 *    BaseListPersenter
 */
abstract class BaseListFragment<T,D,V:View> : BaseFragment(), BaseView<T> {
    private val adapter by lazy { newAdapter()}
    abstract fun newAdapter():BaseListAdapter<D,V>
    private val mPresenter by lazy { newPresenter() }
    abstract fun newPresenter():BasePresenter
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

    override fun onMoreSuccess(list: T?) {
        adapter.moreData(getList(list))
    }

    override fun onError(message: String?) {
        swipeRefreshLayout.isRefreshing = false
        myToast(message)
    }

    override fun onLoadSuccess(list: T?) {
        swipeRefreshLayout.isRefreshing = false
        adapter.initData(getList(list))
    }
    /**
     * 从返回结果中获取列表数据集合
     */
    abstract fun getList(response: T?): List<D>?

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.destroyView()
    }
}