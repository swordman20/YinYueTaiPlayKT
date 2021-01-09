package com.example.yinyuetaiplaykt.adapter

import android.content.Context
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.widget.HomeItemView

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
class HomeAdapter : BaseListAdapter<HomeItemBean, HomeItemView>() {
    override fun setViewData(itemView: HomeItemView, data: HomeItemBean) = itemView.setData(data)
    override fun createItemView(context: Context): HomeItemView = HomeItemView(context)
}