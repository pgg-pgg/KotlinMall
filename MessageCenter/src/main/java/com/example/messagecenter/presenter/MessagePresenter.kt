package com.example.messagecenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.messagecenter.data.protocol.Message
import com.example.messagecenter.presenter.view.MessageView
import com.kotlin.message.service.MessageService
import javax.inject.Inject

/*
    消息列表 Presenter
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    /*
        获取消息列表
     */
    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        messageService.getMessageList().execute(object : BaseSubscriber<MutableList<Message>?>(mView) {
            override fun onNext(t: MutableList<Message>?) {
                mView.onGetMessageResult(t)
            }
        }, lifecycleProvider)

    }


}
