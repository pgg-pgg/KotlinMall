package com.example.usercenter.data.protocol

/**
 * Created by pengganggui on 2018/8/21.
 * 忘记密码请求体
 */
data class ForgetPwdReq(val mobile:String, val verifyCode:String)