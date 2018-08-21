package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.example.baselibrary.ext.enable
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.LoginPresenter
import com.example.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by pengganggui on 2018/8/21.
 * 登录界面
 */
class LoginActivity :BaseMvpActivity<LoginPresenter>(),LoginView,View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt,{isBtnEnable()})
        mLoginBtn.enable(mPwdEt,{isBtnEnable()})

        mHeaderBar.getRightView().onClick ( this )
        mLoginBtn.onClick (this)
        mForgetPwdTv.onClick(this)
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        startActivity<UserInfoActivity>()
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.mLoginBtn ->{
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }
            R.id.mForgetPwdTv->{
                startActivity<ForgetPwdActivity>()
            }
            R.id.mRightTv->{
                startActivity<RegisterActivity>()
            }
        }
    }



    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView=this
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not()&&
                mPwdEt.text.isNullOrEmpty().not()
    }


}