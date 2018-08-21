package com.example.usercenter.data.protocol

/**
 * Created by pengganggui on 2018/8/21.
 * 登录请求体
 */
data class LoginReq(val mobile:String, val pwd:String, val pushId:String)