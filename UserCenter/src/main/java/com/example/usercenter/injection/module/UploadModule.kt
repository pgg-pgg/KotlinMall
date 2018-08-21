package com.example.usercenter.injection.module

import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import com.example.usercenter.service.impl.UploadServiceImpl
import com.example.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/14.
 * UploadService依赖声明类
 */
@Module
class UploadModule {
    /**
     * 提供实例化的方法
     */
    @Provides
    fun providerUploadService(uploadService: UploadServiceImpl):UploadService{
        return uploadService
    }
}