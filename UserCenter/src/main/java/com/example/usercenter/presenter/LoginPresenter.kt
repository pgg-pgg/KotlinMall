package com.example.usercenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.presenter.view.LoginView
import com.example.usercenter.presenter.view.RegisterView
import com.example.usercenter.service.UserService
import com.kotlin.user.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        /**
         * 业务逻辑
         * 请求网络，接收响应，把相应传递给activity
         */
        if (!checkNetWork()) {
            toast("请检查网络后重试！")
            return
        }
        mView.showLoading()
        userService.login(mobile, pwd, pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    //扩展方法
                    override fun onNext(t: UserInfo) {
                        UserPrefsUtils.putUserInfo(t)
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)

    }

}