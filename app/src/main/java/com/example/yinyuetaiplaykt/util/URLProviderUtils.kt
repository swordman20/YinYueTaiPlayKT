package com.example.yinyuetaiplaykt.util

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :https://www.jianshu.com/p/a71f41186aa0
 */
object URLProviderUtils {

    fun getHomeUrl(pageNum:Int,pageSize:Int):String{
        return "https://api.yinyuetai.com/video/getTypeVideoList?videoType=1&pageNum=${pageNum}&pageSize=${pageSize}"
    }
}