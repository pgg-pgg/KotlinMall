package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.data.protocol.CartGoods
import com.example.goodscenter.data.protocol.Goods
import com.example.goodscenter.presenter.view.CartView
import com.example.goodscenter.presenter.view.GoodsView
import com.example.goodscenter.service.CartService
import com.example.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class CartPresenter @Inject constructor() : BasePresenter<CartView>() {

    @Inject
    lateinit var cartService: CartService

    fun getCartList(){
        cartService.getCartList().execute(object :BaseSubscriber<MutableList<CartGoods>?>(mView){
            override fun onNext(t: MutableList<CartGoods>?) {
                mView.onGetCartListResult(t)
            }
        },lifecycleProvider)
    }

    fun deleteCartList(cartIdList: List<Int>){
        cartService.deleteCartList(cartIdList).execute(object :BaseSubscriber<Boolean>(mView){
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListResult(t)
            }
        },lifecycleProvider)
    }

    fun submitCart(goodsList: List<CartGoods>,totalPrice: Long){
        cartService.submitCart(goodsList,totalPrice).execute(object :BaseSubscriber<Int>(mView){
            override fun onNext(t: Int) {
                mView.onSubmitCartListResult(t)
            }
        },lifecycleProvider)
    }



}