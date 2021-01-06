package com.example.yinyuetaiplaykt.ui.fragment

import com.example.yinyuetaiplaykt.adapter.HomeAdapter
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.base.BaseListFragment
import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.presenter.impl.HomePresenterImpl
import com.example.yinyuetaiplaykt.widget.HomeItemView

class HomeFragment :BaseListFragment<List<HomeItemBean>,HomeItemBean,HomeItemView>(){
    override fun newAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun newPresenter(): BasePresenter {
        return HomePresenterImpl(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }

}
