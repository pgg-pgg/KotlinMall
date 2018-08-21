package com.example.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

/**
 * Created by pengganggui on 2018/8/15.
 * Activity级别的Module
 */
@Module
class ActivityModule (private val context: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity():Activity{
        return context
    }
}