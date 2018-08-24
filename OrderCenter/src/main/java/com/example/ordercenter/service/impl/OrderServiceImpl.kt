package com.example.ordercenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.baselibrary.ext.convertBoolean
import com.example.ordercenter.data.protocol.Order
import com.example.ordercenter.data.repository.OrderRepository
import com.example.ordercenter.service.OrderService
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/23.
 */
class OrderServiceImpl @Inject constructor() :OrderService {

    @Inject
    lateinit var repository:OrderRepository

    override fun cancelOrder(orderId: Int): Observable<Boolean> {
        return repository.cancelOrder(orderId).convertBoolean()
    }

    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return repository.confirmOrder(orderId).convertBoolean()
    }

    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }

    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return repository.getOrderList(orderStatus).convert()
    }

    override fun submitOrder(order: Order): Observable<Boolean> {
        return repository.submitOrder(order).convertBoolean()
    }
}