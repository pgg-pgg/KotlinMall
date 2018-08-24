package com.example.ordercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.ordercenter.data.protocol.Order

/**
 * Created by pengganggui on 2018/8/24.
 */
interface OrderListView :BaseView{

    //获取订单列表回调
    fun onGetOrderListResult(result:MutableList<Order>?)

    //确认订单回调
    fun onConfirmOrderResult(result:Boolean)

    //取消订单回调
    fun onCancelOrderResult(result:Boolean)
}