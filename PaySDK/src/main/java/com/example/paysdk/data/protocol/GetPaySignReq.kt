package com.example.paysdk.data.protocol

/*
    获取支付宝 支付签名
 */
data class GetPaySignReq(val orderId: Int, val totalPrice: Long)
