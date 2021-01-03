package com.example.yinyuetaiplaykt.net

import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.util.URLProviderUtils

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
class HomeRequset(type:Int,pageNum:Int, handler: ResponseHandler<BaseListBean>) :MRequest<BaseListBean>(type,URLProviderUtils.getHomeUrl(pageNum,20),handler) {
}