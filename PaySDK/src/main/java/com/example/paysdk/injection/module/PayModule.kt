package com.example.paysdk.injection.module

import com.example.paysdk.service.PayService
import com.example.paysdk.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/*
    支付 Module
 */
@Module
class PayModule {

    @Provides
    fun providePayService(payService: PayServiceImpl): PayService {
        return payService
    }

}
