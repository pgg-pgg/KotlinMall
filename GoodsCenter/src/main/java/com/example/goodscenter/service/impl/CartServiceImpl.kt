package com.example.goodscenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.baselibrary.ext.convertBoolean
import com.example.goodscenter.data.protocol.CartGoods
import com.example.goodscenter.data.repository.CartRepository
import com.example.goodscenter.service.CartService
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/23.
 */
class CartServiceImpl  @Inject constructor() :CartService {

    @Inject
    lateinit var repository: CartRepository


    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId,goodsDesc,goodsIcon,goodsPrice,goodsCount,goodsSku).convert()
    }

    override fun deleteCartList(cartIdList: List<Int>): Observable<Boolean> {
        return repository.deleteCartList(cartIdList).convertBoolean()
    }

    override fun submitCart(goodsList: List<CartGoods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(goodsList,totalPrice).convert()
    }
}