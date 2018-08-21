package com.example.usercenter.service

import com.example.usercenter.data.protocol.UserInfo
import rx.Observable

/**
 * Created by pengganggui on 2018/8/14.
 * 上传图片逻辑层接口
 */
interface UploadService {

    fun getUploadToken():Observable<String>

}