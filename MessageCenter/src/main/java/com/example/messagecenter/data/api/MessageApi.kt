package com.example.messagecenter.data.api

import com.example.baselibrary.data.protocol.BaseResp
import com.example.messagecenter.data.protocol.Message
import rx.Observable
import retrofit2.http.POST

/*
    消息 接口
 */
interface MessageApi {

    /*
        获取消息列表
     */
    @POST("msg/getList")
    fun getMessageList(): Observable<BaseResp<MutableList<Message>?>>

}
