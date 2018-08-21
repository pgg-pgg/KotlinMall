package com.example.baselibrary.injection.module

import android.content.Context
import com.example.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

/**
 * Created by pengganggui on 2018/8/15.
 */
@Module
class AppModule (private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providesContext():Context{
        return context
    }
}