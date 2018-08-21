package com.example.usercenter.data.repository

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResp
import com.example.usercenter.data.api.UserApi
import com.example.usercenter.data.protocol.*
import com.example.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class UserRepository @Inject constructor(){

    /**
     * 注册
     */
    fun register(mobile:String,verify:String,pwd:String):Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile,verify,pwd))
    }

    /**
     * 登录
     */
    fun login(mobile: String,pwd: String,pushId:String):Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(mobile,pwd,pushId))
    }

    /**
     * 忘记密码
     */
    fun forgetPwd(mobile: String,verifyCode: String):Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile,verifyCode))
    }

    /**
     * 重置密码
     */
    fun resetPwd(mobile: String,pwd: String):Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile,pwd))
    }

    /**
     * 修改用户信息
     */
    fun editUser(userIcon: String,userName: String,userGender:String,userSign:String):Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUser(EditUserReq(userIcon,userName,userGender,userSign))
    }
}