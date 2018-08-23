package com.example.goodscenter.service.impl

import com.example.baselibrary.ext.convert
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.data.protocol.Goods
import com.example.goodscenter.data.repository.CategoryRepository
import com.example.goodscenter.data.repository.GoodsRepository
import com.example.goodscenter.service.CategoryService
import com.example.goodscenter.service.GoodsService
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/22.
 */
class GoodsServiceImpl @Inject constructor() :GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId,pageNo).convert()
    }

    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword,pageNo).convert()
    }

    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }
}