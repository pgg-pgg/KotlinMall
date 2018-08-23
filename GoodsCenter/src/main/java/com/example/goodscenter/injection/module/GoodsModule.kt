package com.example.goodscenter.injection.module

import com.example.goodscenter.service.GoodsService
import com.example.goodscenter.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/14.
 * GoodsService依赖声明类
 */
@Module
class GoodsModule {
    /**
     * 提供实例化的方法
     */
    @Provides
    fun providerGoodsService(service: GoodsServiceImpl):GoodsService{
        return service
    }

}