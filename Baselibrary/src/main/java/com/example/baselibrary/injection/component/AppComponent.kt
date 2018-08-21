package com.example.baselibrary.injection.component

import android.content.Context
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by pengganggui on 2018/8/15.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context():Context
}