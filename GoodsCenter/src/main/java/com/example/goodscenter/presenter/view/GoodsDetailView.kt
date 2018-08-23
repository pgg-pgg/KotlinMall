package com.example.goodscenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.goodscenter.data.protocol.Goods

/**
 * Created by pengganggui on 2018/8/23.
 */
interface GoodsDetailView:BaseView {

    /**
     * 获取商品详情
     */
    fun onGetGoodsDetailResult(result:Goods)

    /**
     * 加入购物车
     */
    fun onAddCartResult(result:Int)
}