package com.example.yinyuetaiplaykt.util

import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.base.BaseFragment
import com.example.yinyuetaiplaykt.ui.fragment.HomeFragment
import com.example.yinyuetaiplaykt.ui.fragment.MvFragment
import com.example.yinyuetaiplaykt.ui.fragment.VBangFragment
import com.example.yinyuetaiplaykt.ui.fragment.YueDanFragment

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :管理fragment的工具类
 */
class FragmentUtil private constructor() { //构造方法私有化
   //惰性加载创建的时候只会创建一次（是线程安全的）
    companion object {
        val instance by lazy { FragmentUtil() }
    }

    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vBangFragment by lazy { VBangFragment() }
    val yueDanFragment by lazy { YueDanFragment() }

    /**
     * 根据tabid返回对于的fragment
     */
    fun getFragmentById(tabId:Int):BaseFragment?{
        return when(tabId){
            R.id.tab_home->homeFragment
            R.id.tab_mv->mvFragment
            R.id.tab_vbang->vBangFragment
            R.id.tab_yuedan->yueDanFragment
            else ->null
        }
    }
}