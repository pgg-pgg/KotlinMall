package com.example.goodscenter.data.repository

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResp
import com.example.goodscenter.data.api.GoodsApi
import com.example.goodscenter.data.protocol.GetGoodsDetailReq
import com.example.goodscenter.data.protocol.GetGoodsListByKeywordReq
import com.example.goodscenter.data.protocol.GetGoodsListReq
import com.example.goodscenter.data.protocol.Goods
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/22.
 */
class GoodsRepository  @Inject constructor() {

    fun getGoodsList(categoryId: Int,pageNo: Int):Observable<BaseResp<MutableList<Goods>?>>{
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsList(GetGoodsListReq(categoryId,pageNo))
    }

    fun getGoodsListByKeyword(keyword: String,pageNo: Int):Observable<BaseResp<MutableList<Goods>?>>{
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
    }

    fun getGoodsDetail(goodsId: Int): Observable<BaseResp<Goods>>{
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsDetail(GetGoodsDetailReq(goodsId))
    }
}