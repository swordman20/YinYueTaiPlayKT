package com.example.yinyuetaiplaykt.presenter.impl

import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.base.BaseView
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.net.ResponseHandler
import com.example.yinyuetaiplaykt.net.YueDanRequest
import com.example.yinyuetaiplaykt.presenter.interf.YueDanPresenter

/**
 *    author : hades
 *    date   : 2021/1/4
 *    desc   :
 */
class YueDanPresenterImpl(var view: BaseView<List<HomeItemBean>>?) : YueDanPresenter,
    ResponseHandler<BaseListBean> {
    override fun loadDatas(pageNum: Int) {
        YueDanRequest(BasePresenter.TYPE_INIT_OR_REFRESH, pageNum, this).excute()
    }

    override fun loadMore(pageNum: Int) {
        YueDanRequest(BasePresenter.TYPE_LOAD_MORE, pageNum, this).excute()
    }

    /**
     * 解绑view和presenter
     */
    override fun destroyView() {
        if (view != null) {
            view = null
        }
    }

    override fun onError(type: Int, msg: String?) {
        view?.onError(msg)
    }

    override fun onSuccess(type: Int, result: BaseListBean) {
        if (type == BasePresenter.TYPE_INIT_OR_REFRESH) {
            view?.onLoadSuccess(result.list)
        } else {
            view?.onMoreSuccess(result.list)
        }
    }
}