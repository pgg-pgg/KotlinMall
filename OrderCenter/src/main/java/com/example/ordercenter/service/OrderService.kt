package com.example.ordercenter.service

import com.example.ordercenter.data.protocol.Order
import rx.Observable

/**
 * Created by pengganggui on 2018/8/23.
 */
interface OrderService {

    /**
     * 取消订单
     */
    fun cancelOrder(orderId: Int): Observable<Boolean>

    /**
     * 确认订单
     */
    fun confirmOrder(orderId: Int): Observable<Boolean>


    /**
     * 根据ID查询订单
     */
    fun getOrderById(orderId: Int): Observable<Order>


    /**
     * 获取订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>


    /**
     * 提交订单
     */
    fun submitOrder(order: Order): Observable<Boolean>
}