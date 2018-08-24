package com.example.ordercenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.ordercenter.data.protocol.Order
import com.example.ordercenter.presenter.view.OrderConfirmView
import com.example.ordercenter.service.OrderService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/23.
 */
class OrderComfirmPresenter @Inject constructor(): BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService


    fun getOrderById(orderId: Int){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId).execute(object :BaseSubscriber<Order>(mView){
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        },lifecycleProvider)
    }


    fun submitOrder(order: Order){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order).execute(object :BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onSubmitOrderResult(t)
            }
        },lifecycleProvider)
    }
}