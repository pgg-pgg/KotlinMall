package com.example.ordercenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.utils.NetWorkUtils
import com.example.ordercenter.data.protocol.Order
import com.example.ordercenter.presenter.view.OrderDetailView
import com.example.ordercenter.service.OrderService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/24.
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {


    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderId: Int){
        if (!NetWorkUtils.isNetWorkAvailable(context)){
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId).execute(object :BaseSubscriber<Order>(mView){
            override fun onNext(t: Order) {
                mView.onGetOrderByIdResult(t)
            }
        },lifecycleProvider)
    }
}