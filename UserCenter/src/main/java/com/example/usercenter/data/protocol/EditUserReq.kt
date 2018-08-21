package com.example.usercenter.data.protocol

/**
 * Created by pengganggui on 2018/8/21.
 * 修改用户信息的请求体
 */

data class EditUserReq(val userIcon: String, val userName: String, val gender: String, val sign: String)
