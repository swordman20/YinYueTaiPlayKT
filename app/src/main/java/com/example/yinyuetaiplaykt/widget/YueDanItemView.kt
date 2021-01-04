package com.example.yinyuetaiplaykt.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_yuedan.view.*

/**
 *    author : hades
 *    date   : 2021/1/4
 *    desc   :
 */
class YueDanItemView :RelativeLayout {
    fun setData(homeItemBean: HomeItemBean) {
        title.text = homeItemBean.artistName
        desc.text = homeItemBean.videoDesc
        Picasso.with(context).load(homeItemBean.videoImg).into(bg)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    init {
        View.inflate(context, R.layout.item_yuedan,this)
    }
}