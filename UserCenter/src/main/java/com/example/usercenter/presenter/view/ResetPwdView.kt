package com.example.usercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView

/**
 * Created by pengganggui on 2018/8/21.
 */
interface ResetPwdView :BaseView{

    fun onResetPwdResult(result:String)

    fun toLoginActivity()
}