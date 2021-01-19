package com.example.yinyuetaiplaykt.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 *    author : hades
 *    date   : 2021/1/19
 *    desc   :
 */
class StringUtil {
    companion object{
        fun assetsJsonRead(fileName: String, context: Context?):String{
            val sb = StringBuffer()
            try {
                val open = context?.assets?.open("json/$fileName")
                val isp = InputStreamReader(open)
                val reader = BufferedReader(isp)
                var jsonLine: String?
                while (reader.readLine().also { jsonLine = it } != null) {
                    sb.append(jsonLine)
                }
                reader.close()
                isp.close()
                open?.close()
            } catch (e: NoSuchFieldException) {
                return ""
            }
            return sb.toString()
        }
    }
}