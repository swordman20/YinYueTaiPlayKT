package com.example.yinyuetaiplaykt.base

interface BasePresenter {
    companion object{
        const val TYPE_INIT_OR_REFRESH = 0
        const val TYPE_LOAD_MORE = 1
    }
    fun loadData(pageNum: Int)
    fun loadMore(pageNum: Int)
    fun onDetachView()
}