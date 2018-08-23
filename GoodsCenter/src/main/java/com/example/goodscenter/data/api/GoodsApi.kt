package com.example.goodscenter.data.api


import com.example.baselibrary.data.protocol.BaseResp
import com.example.goodscenter.data.protocol.GetGoodsDetailReq
import com.example.goodscenter.data.protocol.GetGoodsListByKeywordReq
import com.example.goodscenter.data.protocol.GetGoodsListReq
import com.example.goodscenter.data.protocol.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
