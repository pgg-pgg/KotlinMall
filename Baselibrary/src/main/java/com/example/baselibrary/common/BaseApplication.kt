package com.example.baselibrary.common

import android.app.Application
import android.content.Context
import com.example.baselibrary.injection.component.AppComponent
import com.example.baselibrary.injection.component.DaggerAppComponent
import com.example.baselibrary.injection.module.AppModule

/**
 * Created by pengganggui on 2018/8/15.
 * 全局Application
 */
open class BaseApplication :Application() {


    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        context=this
    }

    private fun initAppInjection() {
        appComponent=DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context:Context
    }
}