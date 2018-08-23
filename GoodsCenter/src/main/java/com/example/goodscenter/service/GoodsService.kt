package com.example.goodscenter.service

import com.example.goodscenter.data.protocol.Goods
import rx.Observable

/**
 * Created by pengganggui on 2018/8/22.
 */
interface GoodsService {

    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}