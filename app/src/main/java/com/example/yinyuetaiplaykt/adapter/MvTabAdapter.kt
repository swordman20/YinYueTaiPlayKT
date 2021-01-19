package com.example.yinyuetaiplaykt.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.yinyuetaiplaykt.model.MVTabBean
import com.example.yinyuetaiplaykt.ui.fragment.YueDanFragment

/**
 *    author : hades
 *    date   : 2021/1/19
 *    desc   :
 */
class MvTabAdapter(var list:List<MVTabBean>?,fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size?:0
    }

    override fun getItem(position: Int): Fragment {
        val fragment = YueDanFragment()
        val bundle = Bundle()
        bundle.putString("args",list!![position].name)
        fragment.arguments = bundle
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list!![position].name
    }
}