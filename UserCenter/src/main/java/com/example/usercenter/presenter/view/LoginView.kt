package com.example.usercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.usercenter.data.protocol.UserInfo

/**
 * Created by pengganggui on 2018/8/21.
 */
interface LoginView:BaseView {
    fun onLoginResult(result:UserInfo)
}