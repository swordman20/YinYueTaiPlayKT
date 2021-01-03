package com.example.yinyuetaiplaykt.model.base

import com.example.yinyuetaiplaykt.model.HomeItemBean

data class BaseListBean(
    var pageNum: Int,
    var pageSize: Int,
    var totalCount: Int,
    var list:List<HomeItemBean>
)