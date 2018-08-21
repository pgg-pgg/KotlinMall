package com.example.usercenter.data.api

import com.example.baselibrary.data.protocol.BaseResp
import com.example.usercenter.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by pengganggui on 2018/8/14.
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req:LoginReq):Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req:ResetPwdReq):Observable<BaseResp<String>>


    @POST("userCenter/editUser")
    fun editUser(@Body req:EditUserReq):Observable<BaseResp<UserInfo>>
}