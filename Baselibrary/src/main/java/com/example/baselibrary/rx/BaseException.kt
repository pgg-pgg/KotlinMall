package com.example.baselibrary.rx

/**
 * Created by pengganggui on 2018/8/14.
 * 自定义请求失败的异常
 */
open class BaseException(val status:Int,message:String):Throwable()