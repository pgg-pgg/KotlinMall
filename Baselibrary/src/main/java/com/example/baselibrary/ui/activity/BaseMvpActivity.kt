package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.component.DaggerActivityComponent
import com.example.baselibrary.injection.module.ActivityModule
import com.example.baselibrary.injection.module.LifecycleProviderModule
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.presenter.view.BaseView
import com.example.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 * 业务逻辑的Activity的基类
 */
open abstract class BaseMvpActivity<T:BasePresenter<*>> :BaseActivity(),BaseView {


    override fun showLoading() {
        mProgressLoading.showLoading()
    }

    override fun hideLoading() {
        mProgressLoading.hideLoading()
    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    lateinit var mProgressLoading: ProgressLoading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
        mProgressLoading=ProgressLoading.create(this)
        ARouter.getInstance().inject(this)
    }

    abstract fun injectComponent()
    private fun initActivityInjection() {
        activityComponent=DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}