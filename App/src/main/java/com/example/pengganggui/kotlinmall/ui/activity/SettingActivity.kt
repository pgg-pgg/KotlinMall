package com.example.pengganggui.kotlinmall.ui.activity

import android.os.Bundle
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.pengganggui.kotlinmall.R
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created by pengganggui on 2018/8/22.
 */
class SettingActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mLogoutBtn.onClick{
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}