package com.example.goodscenter.service

import com.example.goodscenter.data.protocol.CartGoods
import rx.Observable

/**
 * Created by pengganggui on 2018/8/23.
 */
interface CartService {

    /**
     * 获取购物车列表
     */
    fun getCartList(): Observable<MutableList<CartGoods>?>

    /**
     * 添加商品到购物车
     */
    fun addCart(goodsId: Int, //商品ID
                goodsDesc: String, //商品描述
                goodsIcon: String, //商品图标
                goodsPrice: Long, //商品价格
                goodsCount: Int, //商品数量
                goodsSku: String //商品SKU
    ): Observable<Int>

    /**
     * 删除商品
     */
    fun deleteCartList(cartIdList: List<Int> = arrayListOf()): Observable<Boolean>

    /**
     * 提交
     */
    fun submitCart(goodsList: List<CartGoods>,totalPrice: Long): Observable<Int>
}