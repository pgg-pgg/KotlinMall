package com.example.provider.router

/**
 * Created by pengganggui on 2018/8/23.
 */
object RouterPath{
    class UserCenter{
        companion object {
            const val PATH_LOGIN="/userCenter/login"
        }
    }


    class OrderCenter{
        companion object {
            const val PATH_ORDER_CONFIRM="/orderCenter/confirms"
        }
    }

    class PaySdk{
        companion object {
            const val PATH_PAY="/paysdk/pay"
        }
    }

    //消息模块
    class MessageCenter{
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }

}