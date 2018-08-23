package com.example.goodscenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.goodscenter.data.protocol.Goods

/**
 * Created by pengganggui on 2018/8/21.
 */
interface GoodsView:BaseView {

    fun onGetGoodsListResult(result:MutableList<Goods>?)

}
