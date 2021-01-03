package com.example.yinyuetaiplaykt.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.widget.HomeItemView
import com.example.yinyuetaiplaykt.widget.LoadMoreView

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var list =
        ArrayList<HomeItemBean>()//在这里创建List的好处是每个adapter只维护自己的list，如果在Fragment里面需要为何好多个List

    /**
     * 更新数据操作
     */
    fun updateList(list: List<HomeItemBean>?) {
        this.list.clear()
        this.list.addAll(list!!)
        notifyDataSetChanged()
    }
    /**
     * 加载更多数据
     */
    fun addMoreList(list: List<HomeItemBean>?) {
//        let == java check null
        list?.let {
            this.list.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        //如果是最后一条数据就不需要刷新数据了
        if (position == list.size) return
        val data = list[position]
        val itemView = holder.itemView as HomeItemView
        // 刷新数据放在自定义view中，不然adapter代码很多
        itemView.setData(data)
    }

    override fun getItemViewType(position: Int): Int {
        //最后一条
        return if (position == list.size) {
            1
        } else {
            //普通item
            0
        }
    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        //最后一条
        return if (viewType == 1) {
            HomeHolder(LoadMoreView(parent.context))
        } else {
            //普通item
            HomeHolder(HomeItemView(parent.context))
        }

    }

    override fun getItemCount(): Int {
        return list.size+1
    }
}