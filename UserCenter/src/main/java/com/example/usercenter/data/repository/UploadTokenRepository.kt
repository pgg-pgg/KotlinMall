package com.example.usercenter.data.repository

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResp
import com.example.usercenter.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class UploadTokenRepository @Inject constructor(){

    /**
     * 获取上传凭证
     */
    fun getUploadToken():Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }
}