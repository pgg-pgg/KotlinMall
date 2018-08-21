package com.example.usercenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.baselibrary.ext.convertBoolean
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.data.repository.UserRepository
import com.example.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 * 用户业务逻辑层接口实现类
 * 通过dagger2进行依赖注入
 */
class UserServiceImpl @Inject constructor():UserService {



    /**
     * 注册方法，通过调用网络请求接口返回的数据，进行解析，分析状态，返回一个注册是否成功的Observable
     */
    @Inject
    lateinit var repository: UserRepository
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return repository.register(mobile,verifyCode,pwd).convertBoolean()
    }

    /**
     * 登录方法
     */
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile,pwd,pushId).convert()
    }

    /**
     * 忘记密码
     */
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile,verifyCode).convertBoolean()
    }

    /**
     * 重置密码
     */
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile,pwd).convertBoolean()
    }

    /**
     * 修改用户信息
     */
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return repository.editUser(userIcon,userName,userGender,userSign).convert()
    }
}
