package com.example.yinyuetaiplaykt.presenter.interf

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
interface YueDanPresenter {
    companion object{
        const val TYPE_INIT_OR_REFRESH = 0
        const val TYPE_LOAD_MORE = 1
    }
    fun loadDatas(pageNum: Int)
    fun loadMore(pageNum: Int)
}