package com.example.goodscenter.injection.module

import com.example.goodscenter.service.CartService
import com.example.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/23.
 */
@Module
class CartModule {


    @Provides
    fun provideCartService(cartService: CartServiceImpl):CartService{
        return cartService
    }
}