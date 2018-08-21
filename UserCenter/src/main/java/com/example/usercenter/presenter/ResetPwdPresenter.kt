package com.example.usercenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.presenter.view.ForgetPwdView
import com.example.usercenter.presenter.view.LoginView
import com.example.usercenter.presenter.view.ResetPwdView
import com.example.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/21.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile:String,pwd:String){
        /**
         * 业务逻辑
         * 请求网络，接收响应，把相应传递给activity
         */
        if(!checkNetWork()){
            toast("请检查网络后重试！")
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile,pwd)
                .execute(object : BaseSubscriber<Boolean>(mView){//扩展方法
                override fun onNext(t: Boolean) {
                    if (t) {
                        mView.onResetPwdResult("重置成功！")
                        mView.toLoginActivity()
                    }
                }
                },lifecycleProvider)

    }
}