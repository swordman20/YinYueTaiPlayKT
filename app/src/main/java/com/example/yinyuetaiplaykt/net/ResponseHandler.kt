package com.example.yinyuetaiplaykt.net

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   : 请求的回调
 */
interface ResponseHandler<T> {
    fun onError(msg:String?)
    //用泛型返回
    fun onSuccess(result:T)
}