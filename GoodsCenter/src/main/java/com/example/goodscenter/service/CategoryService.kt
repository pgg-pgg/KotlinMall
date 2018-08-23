package com.example.goodscenter.service

import com.example.goodscenter.data.protocol.Category
import rx.Observable

/**
 * Created by pengganggui on 2018/8/22.
 */
interface CategoryService {

    fun getCategory(parentId:Int):Observable<MutableList<Category>?>
}