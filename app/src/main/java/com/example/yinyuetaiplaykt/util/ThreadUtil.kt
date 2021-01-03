package com.example.yinyuetaiplaykt.util

import android.os.Handler
import android.os.Looper

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :
 */
object ThreadUtil {
    private val handle = Handler(Looper.getMainLooper())

    /**
     * 运行在主线程中
     */
    fun runOnMainThread(runnable: Runnable){
        handle.post(runnable)
    }
}