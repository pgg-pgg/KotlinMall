package com.example.goodscenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.goodscenter.injection.module.CartModule
import com.example.goodscenter.injection.module.CategoryModule
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.ui.activity.CartActivity
import com.example.goodscenter.ui.activity.GoodsActivity
import com.example.goodscenter.ui.activity.SearchGoodsActivity
import com.example.goodscenter.ui.fragment.CartFragment
import com.example.goodscenter.ui.fragment.CategoryFragment
import com.example.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Created by pengganggui on 2018/8/14.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponent {
    fun inject(fragment:CartFragment)
}