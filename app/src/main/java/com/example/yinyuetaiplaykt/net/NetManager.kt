package com.example.yinyuetaiplaykt.net

import com.example.yinyuetaiplaykt.model.base.BaseListBean
import com.example.yinyuetaiplaykt.util.ThreadUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :发送网络请求的类
 *    使用单例模式
 */
class NetManager private constructor() {
    private val client by lazy { OkHttpClient() } //初始化一次

    //单例的实现，在companion object 静态代码块，注意不是在init里面写
    companion object {
        val instance by lazy { NetManager() }
    }

    /**
     * 发送网络请求
     */
    fun <T> sendRequest(req: MRequest<T>) {
        val request = Request.Builder()
            .url(req.url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            //在子线程
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    req.handler.onError(e.message)
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val parseResult = req.parseResult(result)

                ThreadUtil.runOnMainThread(Runnable {
                    req.handler.onSuccess(parseResult)
                })
            }

        })
    }
}