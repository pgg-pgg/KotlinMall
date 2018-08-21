package com.example.usercenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.presenter.view.UserInfoView
import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class UserInfoPresenter @Inject constructor() :BasePresenter<UserInfoView>() {

    @Inject
    lateinit var uploadService: UploadService

    @Inject
    lateinit var userService:UserService

    /**
     * 得到上传图片的token值
     */
    fun getUploadToken(){
        if (!checkNetWork()){
            return
        }

        mView.showLoading()
        uploadService.getUploadToken().execute(object :BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }

    /**
     * 修改用户信息
     */
    fun editUser(userIcon: String,userName: String,userGender:String,userSign:String){
        if (!checkNetWork()){
            return
        }

        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign).execute(object :BaseSubscriber<UserInfo>(mView){
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        },lifecycleProvider)
    }
}