package com.example.usercenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.usercenter.data.repository.UploadTokenRepository
import com.example.usercenter.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/21.
 * 上传图片业务逻辑层接口实现类
 * 通过dagger2进行依赖注入
 */
class UploadServiceImpl @Inject constructor():UploadService {


    @Inject
    lateinit var repository:UploadTokenRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }
}
