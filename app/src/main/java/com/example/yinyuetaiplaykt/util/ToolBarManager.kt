package com.example.yinyuetaiplaykt.util

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.ui.activity.SettingActivity

/**
 *    author : hades
 *    date   : 2021/1/3
 *    desc   :所有节目ToolBar的管理类
 *    kotlin中interface接口可以实现方法
 */
interface ToolBarManager {
    val toolbar: Toolbar

    /**
     * 初始化MainActivity中的ToolBar
     */
    fun initMainToolBar() {
        toolbar.title = "音悦台"
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnClickListener {
            when (it?.id) {

            }
        }
        toolbar.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.setting -> {
                    toolbar.context.startActivity(
                        Intent(
                            toolbar.context,
                            SettingActivity::class.java
                        )
                    )
                }
            }
            true
        }
    }

    fun initSettingToolBar(){
        toolbar.title = "设置界面"
    }
}