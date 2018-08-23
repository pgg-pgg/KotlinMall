package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.data.protocol.Goods
import com.example.goodscenter.presenter.view.GoodsView
import com.example.goodscenter.service.GoodsService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList( categoryId:Int,pageNo:Int){
        goodsService.getGoodsList(categoryId,pageNo).execute(object :BaseSubscriber<MutableList<Goods>?>(mView){
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        },lifecycleProvider)
    }

    fun getGoodsListByKeyword(keyword: String,pageNo: Int){
        goodsService.getGoodsListByKeyword(keyword,pageNo).execute(object :BaseSubscriber<MutableList<Goods>?>(mView){
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        },lifecycleProvider)
    }



}