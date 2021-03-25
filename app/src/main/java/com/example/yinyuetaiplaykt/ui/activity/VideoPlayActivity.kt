package com.example.yinyuetaiplaykt.ui.activity

import com.example.yinyuetaiplaykt.R
import com.example.yinyuetaiplaykt.base.BaseActivity

class VideoPlayActivity : BaseActivity() {
    var videoId:Int = -1

    override fun getLayoutId(): Int = R.layout.activity_video_play

    override fun initListener() {
        videoId = intent.getIntExtra("id", -1)
    }

    override fun initData() {
        
    }
}