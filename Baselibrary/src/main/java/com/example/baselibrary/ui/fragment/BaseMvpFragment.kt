package com.example.baselibrary.ui.fragment

import android.os.Bundle
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.baselibrary.injection.component.DaggerActivityComponent
import com.example.baselibrary.injection.module.ActivityModule
import com.example.baselibrary.injection.module.LifecycleProviderModule
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
open abstract class BaseMvpFragment<T:BasePresenter<*>> :BaseFragment(),BaseView {


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        initActivityInjection()

    }

    abstract fun injectComponent()
    private fun initActivityInjection() {
        activityComponent=DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}