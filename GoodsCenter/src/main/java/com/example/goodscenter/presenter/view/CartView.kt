package com.example.goodscenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.goodscenter.data.protocol.CartGoods

/**
 * Created by pengganggui on 2018/8/23.
 */
interface CartView:BaseView{

    //获取购物车列表
    fun onGetCartListResult(result:MutableList<CartGoods>?)


    //删除购物车
    fun onDeleteCartListResult(result:Boolean)

    //提交购物车
    fun onSubmitCartListResult(result: Int)

}