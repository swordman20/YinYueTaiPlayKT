package com.example.yinyuetaiplaykt.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.base.BaseListAdapter
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.widget.HomeItemView
import com.example.yinyuetaiplaykt.widget.LoadMoreView

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
class HomeAdapter : BaseListAdapter<HomeItemBean,HomeItemView>(){
    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun setViewData(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

}