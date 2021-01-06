package com.example.yinyuetaiplaykt.base

/**
 *    author : hades
 *    date   : 2021/1/6
 *    desc   :
 */
interface BaseView<T> {
    fun onMoreSuccess(list: T?)
    fun onError(message: String?)
    fun onLoadSuccess(list: T?)
}