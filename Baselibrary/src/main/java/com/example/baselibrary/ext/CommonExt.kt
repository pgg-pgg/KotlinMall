package com.example.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.baselibrary.data.protocol.BaseResp
import com.example.baselibrary.rx.BaseFunc
import com.example.baselibrary.rx.BaseFuncBoolean
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by pengganggui on 2018/8/14.
 * kotlin 扩展方法，扩展Rx Observable的方法
 * 1.哪个类需要扩展 Observable
 * 2.扩展的方法名方法execute
 * 3.是否需要传参
 * 4.是否需要返回值
 */

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){

    this.subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

fun View.onClick(listener:View.OnClickListener){
    this.setOnClickListener(listener)
}

fun View.onClick(method:()->Unit){
    this.setOnClickListener { method }
}

fun <T> Observable<BaseResp<T>>.convert():Observable<T>{
    return this.flatMap(BaseFunc())
}


fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}

fun Button.enable(et:EditText,method:()->Boolean){
    var btn=this
    et.addTextChangedListener(object :DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled=method()
        }
    })
}
