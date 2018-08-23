package com.example.goodscenter.data.repository


import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.protocol.BaseResp
import com.example.goodscenter.data.api.CategoryApi
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class CategoryRepository @Inject constructor(){

    fun getCategory(parentId:Int):Observable<BaseResp<MutableList<Category>?>>{
        return RetrofitFactory.instance.create(CategoryApi::class.java)
                .getCategory(GetCategoryReq(parentId))
    }

}