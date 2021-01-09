package com.example.yinyuetaiplaykt.ui.fragment

import com.example.yinyuetaiplaykt.adapter.HomeAdapter
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.base.BaseListFragment
import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.presenter.impl.HomePresenterImpl
import com.example.yinyuetaiplaykt.view.HomeView
import com.example.yinyuetaiplaykt.widget.HomeItemView

class HomeFragment : BaseListFragment<BaseListBean,HomeItemBean,HomeItemView>(),HomeView<BaseListBean>{
    override fun createAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> = HomeAdapter()

    override fun createPresenter(): BasePresenter = HomePresenterImpl(this)

    override fun getItemData(response: BaseListBean): List<HomeItemBean> = response.list

}