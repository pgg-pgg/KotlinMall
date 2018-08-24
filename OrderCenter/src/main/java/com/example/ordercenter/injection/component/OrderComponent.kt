package com.example.ordercenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.ui.activity.OrderConfirmActivity
import com.example.ordercenter.ui.activity.OrderDetailActivity
import com.example.ordercenter.ui.fragment.OrderFragment
import dagger.Component

/**
 * Created by pengganggui on 2018/8/23.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(OrderModule ::class))
interface OrderComponent {

    fun inject(activity:OrderConfirmActivity)

    fun inject(fragment:OrderFragment)

    fun inject(activity:OrderDetailActivity)
}