package com.example.goodscenter.injection.module

import com.example.goodscenter.service.CategoryService
import com.example.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/14.
 * CategoryService依赖声明类
 */
@Module
class CategoryModule {
    /**
     * 提供实例化的方法
     */
    @Provides
    fun providerCategoryService(service: CategoryServiceImpl):CategoryService{
        return service
    }
}