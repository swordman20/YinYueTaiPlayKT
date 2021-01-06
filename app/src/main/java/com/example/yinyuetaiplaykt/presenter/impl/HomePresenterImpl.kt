package com.example.yinyuetaiplaykt.presenter.impl

import com.example.yinyuetaiplaykt.base.BasePresenter
import com.example.yinyuetaiplaykt.base.BaseView
import com.example.yinyuetaiplaykt.model.HomeItemBean
import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.net.HomeRequset
import com.example.yinyuetaiplaykt.net.ResponseHandler
import com.example.yinyuetaiplaykt.presenter.interf.HomePresenter

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
//这里的var修饰是为了该class里面的所有方法都可以使用homeView，如果不添加var，只有init（构造方法）能使用
class HomePresenterImpl(var homeView: BaseView<List<HomeItemBean>>?) : HomePresenter, ResponseHandler<BaseListBean> {

    /**
     * 解绑view和presenter
     */
    override fun destroyView(){
        if (homeView != null) {
            homeView = null
        }
    }

    //进一步简化
    override fun loadDatas(pageNum: Int) {
        HomeRequset(BasePresenter.TYPE_INIT_OR_REFRESH, pageNum,this).excute()
    }

    override fun loadMore(pageNum: Int) {
        HomeRequset(BasePresenter.TYPE_LOAD_MORE,pageNum, this).excute()
    }

    override fun onSuccess(type:Int,result: BaseListBean) {
        when(type){
            BasePresenter.TYPE_INIT_OR_REFRESH->homeView?.onLoadSuccess(result.list)
            BasePresenter.TYPE_LOAD_MORE->homeView?.onMoreSuccess(result.list)
        }
    }

    override fun onError(type:Int,msg: String?) {
        homeView?.onError(msg)
    }


    //使用自己封装的网络请求
//    override fun loadDatas() {
//        //1，创建一个Reques,回调自定义的ResponseHandler
//        val request = HomeRequset(1, object : ResponseHandler<BaseListBean> {
//            //网络框架已经处理了在主线程了
//            override fun onError(msg: String?) {
//                homeView.onError(msg)
//            }
//
//            override fun onSuccess(result: BaseListBean) {
//                homeView.onLoadSuccess(result.list)
//            }
//
//        }).excute()
//        //2,发送网络请求
////        NetManager.instance.sendRequest(request)
//    }

//    override fun loadMore(pageNum: Int) {
//        //1，创建一个Reques,回调自定义的ResponseHandler
//        val request = HomeRequset(pageNum, object : ResponseHandler<BaseListBean> {
//            override fun onError(msg: String?) {
//                homeView.onError(msg)
//            }
//
//            override fun onSuccess(result: BaseListBean) {
//                homeView.onMoreSuccess(result.list)
//            }
//
//        })
//        //2,发送网络请求
////        NetManager.instance.sendRequest(request)
//    }
//    override fun loadDatas() {
//
//        val path = URLProviderUtils.getHomeUrl(1, 20)
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(path)
//            .get()
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            //在子线程
//            override fun onFailure(call: Call, e: IOException) {
//                ThreadUtil.runOnMainThread(Runnable {
//                    homeView.onError(e.message)
//                })
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val result = response.body?.string()
//                val gson = Gson()
//                //这一点和Java使用有一点不同
//                val listBean =
//                    gson.fromJson<BaseListBean>(result, object : TypeToken<BaseListBean>() {}.type)
//
//                ThreadUtil.runOnMainThread(Runnable {
//                    homeView.onLoadSuccess(listBean.list)
//                })
//            }
//
//        })
//
//    }

//    override fun loadMore(pageNum: Int) {
//
//        val path = URLProviderUtils.getHomeUrl(pageNum, 20)
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url(path)
//            .get()
//            .build()
//        client.newCall(request).enqueue(object : Callback {
//            //在子线程
//            override fun onFailure(call: Call, e: IOException) {
//                ThreadUtil.runOnMainThread(Runnable {
//                    homeView.onError(e.message)
//                })
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val result = response.body?.string()
//
//                val gson = Gson()
//                //这一点和Java使用有一点不同
//                val listBean =
//                    gson.fromJson<BaseListBean>(result, object : TypeToken<BaseListBean>() {}.type)
//
//                ThreadUtil.runOnMainThread(Runnable {
//
//                    homeView.onMoreSuccess(listBean.list)
//                })
//            }
//
//        })
//
//    }

}