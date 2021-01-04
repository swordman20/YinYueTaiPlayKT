package com.example.yinyuetaiplaykt.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.widget.LoadMoreView
import com.example.yinyuetaiplaykt.widget.YueDanItemView

/**
 *    author : hades
 *    date   : 2021/1/4
 *    desc   :
 */
class YueDanAdapter : RecyclerView.Adapter<YueDanAdapter.YueDanHolder>() {

    private var list = ArrayList<HomeItemBean>()

    fun initData(list: List<HomeItemBean>?) {
        this.list.clear()
        list?.let {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun moreData(list: List<HomeItemBean>?) {
        list?.let { this.list.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            1
        } else {
            0
        }
    }

    class YueDanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return if (viewType == 1) {
            YueDanHolder(LoadMoreView(parent.context))
        } else {
            YueDanHolder(YueDanItemView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
        if (position == list.size) return
        val homeItemBean = list[position]
        val yueDanItemView = holder.itemView as YueDanItemView
        yueDanItemView.setData(homeItemBean)
    }
}