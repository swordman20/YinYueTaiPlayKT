package com.example.yinyuetaiplaykt.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.example.yinyuetaiplaykt.adapter.YueDanAdapter
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.base.BaseListFragment
import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.presenter.impl.YueDanPresenterImpl
import com.example.yinyuetaiplaykt.ui.activity.VideoPlayActivity
import com.example.yinyuetaiplaykt.view.YueDanView
import com.example.yinyuetaiplaykt.widget.YueDanItemView

class YueDanFragment : BaseListFragment<BaseListBean, HomeItemBean, YueDanItemView>(),YueDanView<BaseListBean> {
    override fun createAdapter(): BaseListAdapter<HomeItemBean, YueDanItemView> = YueDanAdapter()

    override fun createPresenter(): BasePresenter = YueDanPresenterImpl(this)

    override fun getItemData(response: BaseListBean): List<HomeItemBean> = response.list

    override fun initListener() {
        super.initListener()
        adapter.setMyListener {
            var bundle = Bundle()
            bundle.putInt("id",it.id)
            startActivity(Intent(context,VideoPlayActivity::class.java),bundle)
        }
    }
}