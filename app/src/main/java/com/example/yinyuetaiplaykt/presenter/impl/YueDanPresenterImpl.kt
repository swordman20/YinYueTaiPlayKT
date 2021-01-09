package com.example.yinyuetaiplaykt.presenter.impl

import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.base.BaseView
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.net.ResponseHandler
import com.example.yinyuetaiplaykt.net.YueDanRequest
import com.example.yinyuetaiplaykt.presenter.interf.YueDanPresenter
import com.example.yinyuetaiplaykt.view.YueDanView

/**
 *    author : hades
 *    date   : 2021/1/4
 *    desc   :
 */
class YueDanPresenterImpl(var yuedanView: YueDanView<BaseListBean>?) : YueDanPresenter,
    ResponseHandler<BaseListBean> {
    override fun loadData(pageNum: Int) {
        YueDanRequest(BasePresenter.TYPE_INIT_OR_REFRESH,pageNum,this).excute()
    }

    override fun loadMore(pageNum: Int) {
        YueDanRequest(BasePresenter.TYPE_LOAD_MORE,pageNum,this).excute()
    }

    override fun onError(type: Int, msg: String?) {
        yuedanView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: BaseListBean) {
        when (type) {
            BasePresenter.TYPE_INIT_OR_REFRESH -> result?.let { yuedanView?.onLoadSuccess(result) }
            BasePresenter.TYPE_LOAD_MORE -> result?.let { yuedanView?.onMoreSuccess(result) }
        }
    }
    override fun onDetachView() {
        yuedanView?.let {
            yuedanView = null
        }
    }

}