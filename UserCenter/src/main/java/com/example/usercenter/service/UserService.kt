package com.example.usercenter.service

import com.example.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Created by pengganggui on 2018/8/14.
 * 用户业务逻辑层接口
 */
interface UserService {

    fun register(mobile:String,verifyCode:String,pwd:String):Observable<Boolean>

    fun login(mobile: String,pwd: String,pushId:String):Observable<UserInfo>

    fun forgetPwd(mobile: String,verifyCode: String):Observable<Boolean>

    fun resetPwd(mobile: String,pwd: String):Observable<Boolean>

    fun editUser(userIcon: String,userName: String,userGender:String,userSign:String):Observable<UserInfo>
}