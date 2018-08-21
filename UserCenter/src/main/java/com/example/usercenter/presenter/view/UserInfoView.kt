package com.example.usercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.usercenter.data.protocol.UserInfo

/**
 * Created by pengganggui on 2018/8/21.
 */
interface UserInfoView:BaseView {
    fun onGetUploadTokenResult(result:String)

    fun onEditUserResult(userInfo: UserInfo)
}