package com.example.baselibrary.data.protocol

/**
 * Created by pengganggui on 2018/8/14.
 * 所有请求响应的超类
 * 1.out 协变，相当于java中的? extends T，只能读取，不能写入
 * 2.in 逆变，相当于java中的? super T，只能写入，不能读取
 */
class BaseResp<out T>(val status:Int,val message:String,val data:T)