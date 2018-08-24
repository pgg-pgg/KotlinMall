package com.example.messagecenter.service.impl


import com.example.baselibrary.ext.convert
import com.example.messagecenter.data.protocol.Message
import com.example.messagecenter.data.repository.MessageRepository
import com.example.messagecenter.service.MessageService
import javax.inject.Inject

import rx.Observable

/*
   消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
        获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}
