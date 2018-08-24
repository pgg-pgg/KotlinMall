package com.example.ordercenter.injection.component

import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.example.ordercenter.injection.module.ShipAddressModule
import com.example.ordercenter.ui.activity.ShipAddressActivity
import com.example.ordercenter.ui.activity.ShipAddressEditActivity
import dagger.Component

/*
    收货人信息Component
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressEditActivity)

    fun inject(activity: ShipAddressActivity)
}
