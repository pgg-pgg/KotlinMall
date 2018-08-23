package com.example.goodscenter.data.repository

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResp
import com.example.goodscenter.data.api.CartApi
import com.example.goodscenter.data.api.GoodsApi
import com.example.goodscenter.data.protocol.*
import retrofit2.http.Body
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/22.
 */
class CartRepository  @Inject constructor() {

    /**
     * 获取购物车列表
     */
    fun getCartList():Observable<BaseResp<MutableList<CartGoods>?>>{
        return RetrofitFactory.instance.create(CartApi::class.java)
                .getCartList()
    }

    /**
     * 添加商品到购物车
     */
    fun addCart( goodsId: Int, //商品ID
                 goodsDesc: String, //商品描述
                 goodsIcon: String, //商品图标
                 goodsPrice: Long, //商品价格
                 goodsCount: Int, //商品数量
                 goodsSku: String //商品SKU
    ):Observable<BaseResp<Int>>{
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCart(AddCartReq(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku))
    }

    /**
     * 删除商品
     */
    fun deleteCartList(cartIdList: List<Int> = arrayListOf()): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(CartApi::class.java)
                .deleteCartList(DeleteCartReq(cartIdList))
    }

    /**
     * 提交
     */
    fun submitCart(goodsList: List<CartGoods>,totalPrice: Long): Observable<BaseResp<Int>>{
        return RetrofitFactory.instance.create(CartApi::class.java)
                .submitCart(SubmitCartReq(goodsList,totalPrice))
    }
}