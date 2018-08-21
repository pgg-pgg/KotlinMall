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
import com.example.usercenter.presenter.ResetPwdPresenter
import com.example.usercenter.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

/**
 * Created by pengganggui on 2018/8/21.
 * 重置密码界面
 */
class ResetPwdActivity:BaseMvpActivity<ResetPwdPresenter>(),ResetPwdView {

    lateinit var mMobileNum:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    private fun initView() {
        mMobileNum = intent.extras.get(BaseConstant.MOBILE_NUM).toString()

        mConfirmBtn.enable(mPwdEt,{isBtnEnable()})
        mConfirmBtn.enable(mPwdConfirmEt,{isBtnEnable()})

        mConfirmBtn.setOnClickListener{
            mPresenter.resetPwd(mMobileNum,mPwdEt.text.toString())
        }


    }

    override fun onResetPwdResult(result: String) {
        toast(result)
    }

    override fun toLoginActivity() {
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
        finish()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView=this
    }

    private fun isBtnEnable():Boolean{
        return mPwdEt.text.toString().isNullOrEmpty().not()&&
                mPwdConfirmEt.text.toString().isNullOrEmpty().not()
    }
}