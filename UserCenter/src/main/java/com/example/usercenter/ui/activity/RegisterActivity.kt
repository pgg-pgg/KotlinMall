package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.example.baselibrary.common.AppManager
import com.example.baselibrary.ext.enable
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.RegisterPresenter
import com.example.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by pengganggui on 2018/8/14.
 * 注册活动
 */
class RegisterActivity :BaseMvpActivity<RegisterPresenter>(),RegisterView,View.OnClickListener {
    private var pressTime:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //通过dagger2依赖注入，可以直接删去下面一行代码
       //        mPresenter=RegisterPresenter()
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})

        mRegisterBtn.onClick (this)
        mVerifyCodeBtn.onClick (this)
    }

    /**
     * dagger2
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView=this
    }

    /**
     * 注册请求接口返回
     */
    override fun onRegisterResult(result: String){
        toast(result)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.mRegisterBtn -> {
                if(mPwdEt.text.toString() == mPwdConfirmEt.text.toString()){
                    mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
                }else{
                    mPwdEt.text.clear()
                    mPwdConfirmEt.text.clear()
                    toast("两次输入的密码不一致，请重新输入")
                }
            }
            R.id.mVerifyCodeBtn->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
        }
    }

    /**
     * 监听返回键
     */
    override fun onBackPressed() {
        super.onBackPressed()
        val time=System.currentTimeMillis()
        if (time-pressTime>2000){
            toast("再按一次退出程序")
            pressTime=time
        }else{
            AppManager.instance.exitApp(this)
        }
    }

    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not()&&
                mVerifyCodeEt.text.isNullOrEmpty().not()&&
                mPwdEt.text.isNullOrEmpty().not()&&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    override fun toLoginActivity() {
        startActivity<LoginActivity>()
        finish()
    }
}