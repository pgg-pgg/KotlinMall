package com.example.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by pengganggui on 2018/8/15.
 */
@Module
class LifecycleProviderModule (private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider():LifecycleProvider<*>{
        return this.lifecycleProvider
    }
}