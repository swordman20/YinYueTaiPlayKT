package com.example.yinyuetaiplaykt.base

import com.example.yinyuetaiplaykt.model.HomeItemBean

/**
 * @ClassName: BaseView
 * User: xiaxiaoge
 * Date: 2021/1/8
 * @Description:
 */
interface BaseView<RESPONSE> {
    fun onMoreSuccess(respone: RESPONSE)
    fun onError(msg: String?)
    fun onLoadSuccess(respone: RESPONSE)
}