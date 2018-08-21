package com.example.baselibrary.rx

import com.example.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by pengganggui on 2018/8/14.
 * 所有的被观察者的基类
 */
open class BaseSubscriber<T>(val baseVew:BaseView):Subscriber<T>(){
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        baseVew.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseVew.hideLoading()
        if (e is BaseException){
            baseVew.onError(e.message!!)
        }
    }
}

