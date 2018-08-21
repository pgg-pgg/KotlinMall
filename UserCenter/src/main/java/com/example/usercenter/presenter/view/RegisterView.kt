package com.example.usercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView

/**
 * Created by pengganggui on 2018/8/14.
 */
interface RegisterView :BaseView{

    fun onRegisterResult(result:String)

    fun toLoginActivity()
}