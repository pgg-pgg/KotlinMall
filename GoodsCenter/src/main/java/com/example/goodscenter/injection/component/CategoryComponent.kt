package com.example.goodscenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.goodscenter.injection.module.CategoryModule
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.ui.activity.GoodsActivity
import com.example.goodscenter.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by pengganggui on 2018/8/14.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CategoryModule::class,GoodsModule::class))
interface CategoryComponent {

    fun inject(fragment:CategoryFragment)

    fun inject(activity:GoodsActivity)
}