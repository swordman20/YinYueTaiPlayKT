package com.example.yinyuetaiplaykt.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 *    author : hades
 *    date   : 2021/1/18
 *    desc   :
 */
class StringUtil {
    companion object{
        fun AssetsGetJson(fileName: String, context: Context?):String{
            val sb = StringBuffer()
            try {
                val inputStream = context?.assets?.open(fileName)
                val inputStreamReader = InputStreamReader(inputStream)
                val br = BufferedReader(inputStreamReader)
                var jsonLine: String?
                while (br.readLine().also { jsonLine = it } != null) {
                    sb.append(jsonLine)
                }
                br.close()
                inputStreamReader.close()
                inputStream?.close()
            } catch (e: Exception) {
                return ""
            }
            return sb.toString()
        }
    }
}