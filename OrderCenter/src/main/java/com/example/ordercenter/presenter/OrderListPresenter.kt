package com.example.ordercenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.ordercenter.data.protocol.Order
import com.example.ordercenter.presenter.view.OrderListView
import com.example.ordercenter.service.OrderService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/24.
 */
class OrderListPresenter @Inject constructor() :BasePresenter<OrderListView>(){

    @Inject
    lateinit var orderService: OrderService


    /**
     * 根据订单状态获取订单列表
     */
    fun getOrderList(orderStatus:Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus).execute(object :BaseSubscriber<MutableList<Order>?>(mView){
            override fun onNext(t: MutableList<Order>?) {
                mView.onGetOrderListResult(t)
            }
        },lifecycleProvider)
    }

    /*
       订单确认收货
    */
    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        }, lifecycleProvider)

    }
}