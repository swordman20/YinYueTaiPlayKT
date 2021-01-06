package com.example.yinyuetaiplaykt.adapter

import android.content.Context
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.widget.YueDanItemView

/**
 *    author : hades
 *    date   : 2021/1/4
 *    desc   :
 */
class YueDanAdapter : BaseListAdapter<HomeItemBean,YueDanItemView>() {
    override fun getItemView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }

    override fun setViewData(itemView: YueDanItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

}