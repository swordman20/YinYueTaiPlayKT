package com.example.yinyuetaiplaykt.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
class HomeItemView :RelativeLayout {

    /**
     * 熟悉item
     */
    fun setData(data: HomeItemBean) {
        title.text = data.artistName
        desc.text = data.videoDesc
        Picasso.with(context).load(data.videoImg).into(bg)
    }

    constructor(context: Context?) : super(context)//代码创建对象的时候需要的调用的构造方法
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)//xml中需要用的构造方法
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(//带主题的构造方法
        context,
        attrs,
        defStyleAttr
    )

    /**
     * 初始化方法，类似Java静态代码块
     */
    init {
        View.inflate(context, R.layout.item_home,this)
    }
}