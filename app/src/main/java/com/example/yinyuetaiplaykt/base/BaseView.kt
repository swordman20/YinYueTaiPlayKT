package com.example.yinyuetaiplaykt.base

/**
 * @ClassName: BaseView
 * User: xiaxiaoge
 * Date: 2021/1/8
 * @Description:
 */
interface BaseView<RESPONSE> {
    fun onMoreSuccess(list: RESPONSE)
    fun onError(msg: String)
    fun onLoadSuccess(list: RESPONSE)
}