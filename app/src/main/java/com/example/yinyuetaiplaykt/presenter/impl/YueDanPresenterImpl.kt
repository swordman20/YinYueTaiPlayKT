package com.example.yinyuetaiplaykt.presenter.impl

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
class YueDanPresenterImpl(var yuedanView: YueDanView?) : YueDanPresenter,
    ResponseHandler<BaseListBean> {
    override fun loadDatas(pageNum: Int) {
        YueDanRequest(YueDanPresenter.TYPE_INIT_OR_REFRESH,pageNum,this).excute()
    }

    override fun loadMore(pageNum: Int) {
        YueDanRequest(YueDanPresenter.TYPE_LOAD_MORE,pageNum,this).excute()
    }

    /**
     * 解绑view和presenter
     */
    fun destroyView() {
        if (yuedanView != null) {
            yuedanView = null
        }
    }

    override fun onError(type: Int, msg: String?) {
        yuedanView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: BaseListBean) {
        if (type==YueDanPresenter.TYPE_INIT_OR_REFRESH){
            yuedanView?.onLoadSuccess(result.list)
        }else{
            yuedanView?.onMoreSuccess(result.list)
        }
    }
}