package com.example.usercenter.data.protocol

/**
 * Created by pengganggui on 2018/8/14.
 * 注册请求体
 */
data class RegisterReq(val mobile:String,val verifyCode:String,val pwd:String)