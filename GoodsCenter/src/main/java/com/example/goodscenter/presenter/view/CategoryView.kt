package com.example.goodscenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.goodscenter.data.protocol.Category

/**
 * Created by pengganggui on 2018/8/21.
 */
interface CategoryView:BaseView {

    fun onGetCategoryResult(result:MutableList<Category>?)

}
