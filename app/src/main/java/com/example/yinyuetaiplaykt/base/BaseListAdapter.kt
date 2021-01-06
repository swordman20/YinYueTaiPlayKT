package com.example.yinyuetaiplaykt.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.widget.LoadMoreView

/**
 *    author : hades
 *    date   : 2021/1/6
 *    desc   :
 */
abstract class BaseListAdapter<T,V:View> : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {

    private var list = ArrayList<T>()

    fun initData(list: List<T>?) {
        this.list.clear()
        list?.let {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun moreData(list: List<T>?) {
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

    class BaseListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder {
        return if (viewType == 1) {
            BaseListHolder(LoadMoreView(parent.context))
        } else {
            BaseListHolder(getItemView(parent.context))
        }
    }

    abstract fun getItemView(context: Context?): V 

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: BaseListHolder, position: Int) {
        if (position == list.size) return
        val data:T = list[position]
        val itemView = holder.itemView as V
        setViewData(itemView,data)
    }

    abstract fun setViewData(itemView: V, data: T)

}