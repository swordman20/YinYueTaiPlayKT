package com.example.yinyuetaiplaykt.view

import com.example.yinyuetaiplaykt.model.HomeItemBean

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
interface HomeView {
    fun onMoreSuccess(list: List<HomeItemBean>?)
    fun onError(message: String?)
    fun onLoadSuccess(list: List<HomeItemBean>?)
}