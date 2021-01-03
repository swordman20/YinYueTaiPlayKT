package com.example.yinyuetaiplaykt.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.base.BaseActivity
import com.example.yinyuetaiplaykt.util.ToolBarManager

class SettingActivity : BaseActivity() ,ToolBarManager{
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    override fun initData() {
        super.initData()
        initSettingToolBar()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val pust = sp.getBoolean("push", false)
        println("pust=${pust}")
    }
}