package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.ext.enable
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.ForgetPwdPresenter
import com.example.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by pengganggui on 2018/8/21.
 * 忘记密码界面
 */
class ForgetPwdActivity:BaseMvpActivity<ForgetPwdPresenter>(),ForgetPwdView,View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {
        mNextBtn.enable(mMobileEt,{isBtnEnable()})
        mNextBtn.enable(mVerifyCodeEt,{isBtnEnable()})

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView=this
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功！")
            }

            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not()&&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun toResetPwdActivity() {
        startActivity<ResetPwdActivity>(BaseConstant.MOBILE_NUM to mMobileEt.text.toString())
    }
}