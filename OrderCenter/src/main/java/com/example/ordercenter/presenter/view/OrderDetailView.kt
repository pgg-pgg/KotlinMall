package com.example.ordercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.ordercenter.data.protocol.Order

/**
 * Created by pengganggui on 2018/8/24.
 * 订单详情页视图回调
 */
interface OrderDetailView:BaseView {

    fun onGetOrderByIdResult(result: Order)
}