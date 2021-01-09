package com.example.yinyuetaiplaykt.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.widget.LoadMoreView

abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW : View> : RecyclerView.Adapter<BaseListAdapter.ViewHolder>() {

    private var list = ArrayList<ITEMBEAN>()

    fun setData(list: List<ITEMBEAN>?) {
        this.list.clear()
        list?.let {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addData(list: List<ITEMBEAN>?) {
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == 1) {
            ViewHolder(LoadMoreView(parent.context))
        } else {
            ViewHolder(createItemView(parent.context))
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == list.size) return
        val data = list[position]
        val itemView = holder.itemView as ITEMVIEW
        data?.let {
            setViewData(itemView, data)
        }
    }

    abstract fun setViewData(itemView: ITEMVIEW, data: ITEMBEAN)
    abstract fun createItemView(context: Context): ITEMVIEW
}