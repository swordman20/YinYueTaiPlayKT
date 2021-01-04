package com.example.yinyuetaiplaykt.net

import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.util.URLProviderUtils

/**
 *    author : hades
 *    date   : 2021/1/4
 *    desc   :
 */
class YueDanRequest(type: Int, pageNum: Int, handler: ResponseHandler<BaseListBean>) :
    MRequest<BaseListBean>(type, URLProviderUtils.getYueDanUrl(pageNum, 20), handler) {
}