package com.example.yinyuetaiplaykt.presenter.impl

import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.base.BaseView
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.net.HomeRequset
import com.example.yinyuetaiplaykt.net.ResponseHandler
import com.example.yinyuetaiplaykt.presenter.interf.HomePresenter
import com.example.yinyuetaiplaykt.view.HomeView

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
//这里的var修饰是为了该class里面的所有方法都可以使用homeView，如果不添加var，只有init（构造方法）能使用
class HomePresenterImpl(var homeView: HomeView<BaseListBean>?) : HomePresenter, ResponseHandler<BaseListBean> {

    //进一步简化
    override fun loadData(pageNum: Int) {
        HomeRequset(BasePresenter.TYPE_INIT_OR_REFRESH, pageNum, this).excute()
    }

    override fun loadMore(pageNum: Int) {
        HomeRequset(BasePresenter.TYPE_LOAD_MORE, pageNum, this).excute()
    }

    override fun onSuccess(type: Int, result: BaseListBean) {
        when (type) {
            BasePresenter.TYPE_INIT_OR_REFRESH -> result?.let { homeView?.onLoadSuccess(result) }
            BasePresenter.TYPE_LOAD_MORE -> result?.let { homeView?.onMoreSuccess(result) }
        }
    }

    override fun onError(type: Int, msg: String?) {
        homeView?.onError(msg)
    }

    override fun onDetachView() {
        homeView?.let {
            homeView = null
        }
    }
}