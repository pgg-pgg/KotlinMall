package com.example.messagecenter.injection.component


import com.example.baselibrary.injection.PerComponentScope
import com.example.baselibrary.injection.component.ActivityComponent
import com.kotlin.message.injection.module.MessageModule
import com.kotlin.message.ui.fragment.MessageFragment
import dagger.Component

/*
    消息模块注入组件
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(MessageModule::class))
interface MessageComponent{
    fun inject(fragment:MessageFragment)
}
