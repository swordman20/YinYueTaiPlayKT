package com.example.yinyuetaiplaykt.net

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   : 请求的回调
 */
interface ResponseHandler<T> {
    fun onError(type:Int,msg:String?)
    //用泛型返回
    fun onSuccess(type:Int,result:T)
}