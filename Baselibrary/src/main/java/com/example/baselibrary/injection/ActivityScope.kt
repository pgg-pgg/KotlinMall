package com.example.baselibrary.injection

import java.lang.annotation.Documented
import javax.inject.Scope
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by pengganggui on 2018/8/15.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope