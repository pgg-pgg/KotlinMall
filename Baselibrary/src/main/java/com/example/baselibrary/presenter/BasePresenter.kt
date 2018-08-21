package com.example.baselibrary.presenter

import android.app.Activity
import android.content.Context
import com.example.baselibrary.presenter.view.BaseView
import com.example.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
open class BasePresenter<T:BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context:Context

    @Inject
    lateinit var activity:Activity

    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }

    fun toast(message:String){
        activity.toast(message)
    }
}