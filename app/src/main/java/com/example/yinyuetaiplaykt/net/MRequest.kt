package com.example.yinyuetaiplaykt.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :所有请求的基类
 */
open class MRequest<T>(val type:Int,val url: String, var handler: ResponseHandler<T>) {
    /**
     * 解析网络请求的结果
     */
    fun parseResult(result: String?): T {
        //获取泛型类型
        val parameterizedType = javaClass.genericSuperclass as ParameterizedType
        //获取class的第一个泛型的类型
        val type = parameterizedType.actualTypeArguments[0]
        val gson = Gson()
        return gson.fromJson(result, type)
    }

    /**
     * 发送网络请求
     */
    fun excute() {
        NetManager.instance.sendRequest(this)
    }
}