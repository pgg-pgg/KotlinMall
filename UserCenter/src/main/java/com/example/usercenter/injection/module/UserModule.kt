package com.example.usercenter.injection.module

import com.example.usercenter.service.UserService
import com.example.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/14.
 * UserService依赖声明类
 */
@Module
class UserModule {
    /**
     * 提供实例化的方法
     */
    @Provides
    fun providerUserService(service: UserServiceImpl):UserService{
        return service
    }
}