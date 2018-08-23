package com.example.baselibrary.ext

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.baselibrary.R
import com.example.baselibrary.data.protocol.BaseResp
import com.example.baselibrary.rx.BaseFunc
import com.example.baselibrary.rx.BaseFuncBoolean
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.utils.GlideUtils
import com.example.baselibrary.widgets.DefaultTextWatcher
import com.kennyc.view.MultiStateView
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
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

fun View.onClick(listener:View.OnClickListener):View{
    this.setOnClickListener(listener)
    return this
}

fun View.onClick(method:()->Unit):View{
    this.setOnClickListener { method() }
    return this
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

fun ImageView.loadUrl(context:Context,url:String){
    GlideUtils.loadUrlImage(context,url,this)
}


/*
    多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
