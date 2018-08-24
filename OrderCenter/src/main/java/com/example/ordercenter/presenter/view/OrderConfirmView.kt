package com.example.ordercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.ordercenter.data.protocol.Order

/**
 * Created by pengganggui on 2018/8/23.
 * 订单确认页，视图回调
 */
interface OrderConfirmView :BaseView {

    //获取订单回调
    fun onGetOrderByIdResult(result:Order)

    //提交订单回调
    fun onSubmitOrderResult(result:Boolean)
}