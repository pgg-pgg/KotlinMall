package com.example.baselibrary.presenter.view

/**
 * Created by pengganggui on 2018/8/14.
 */
interface BaseView {
    //显示对话框
    fun showLoading()
    //隐藏对话框
    fun hideLoading()

    //出错
    fun onError(message:String)
}