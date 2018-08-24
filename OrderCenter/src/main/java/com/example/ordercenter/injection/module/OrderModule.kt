package com.example.ordercenter.injection.module

import com.example.ordercenter.service.OrderService
import com.example.ordercenter.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/23.
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderService(orderService: OrderServiceImpl): OrderService {
        return orderService
    }
}