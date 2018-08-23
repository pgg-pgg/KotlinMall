package com.example.goodscenter.presenter

import com.example.baselibrary.ext.execute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.goodscenter.data.protocol.Category
import com.example.goodscenter.presenter.view.CategoryView
import com.example.goodscenter.service.CategoryService
import javax.inject.Inject

/**
 * Created by pengganggui on 2018/8/14.
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory( parentId:Int){
        categoryService.getCategory(parentId).execute(object :BaseSubscriber<MutableList<Category>?>(mView){
            override fun onNext(t: MutableList<Category>?) {
                mView.onGetCategoryResult(t)
            }
        },lifecycleProvider)
    }

}