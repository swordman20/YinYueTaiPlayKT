package com.example.yinyuetaiplaykt.ui.fragment

import com.example.yinyuetaiplaykt.adapter.YueDanAdapter
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.base.BaseListFragment
import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.presenter.impl.YueDanPresenterImpl
import com.example.yinyuetaiplaykt.widget.YueDanItemView

class YueDanFragment : BaseListFragment<List<HomeItemBean>,HomeItemBean, YueDanItemView>() {
    override fun newAdapter(): BaseListAdapter<HomeItemBean, YueDanItemView> {
        return YueDanAdapter()
    }

    override fun newPresenter(): BasePresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean>? {
        return response
    }


}