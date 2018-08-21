package com.example.usercenter.data.api

import com.example.baselibrary.data.protocol.BaseResp
import com.example.usercenter.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by pengganggui on 2018/8/14.
 */
interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken():Observable<BaseResp<String>>
}