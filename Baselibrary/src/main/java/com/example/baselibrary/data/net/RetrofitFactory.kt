package com.example.baselibrary.data.net

import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by pengganggui on 2018/8/14.
 * Retrofit工厂类，负责位每一个网络请求接口生产Retrofit实例
 * kotlin 单例模式写法
 * 1.私有化构造方法
 * 2.在伴生对象中延迟初始化一个实例，相当于java中的静态内部类的单例写法
 */
class RetrofitFactory private constructor(){
    //单例模式
    companion object {
        val instance:RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit:Retrofit
    private val interceptor:Interceptor

    //初始化方法
    init {
        //Http header 拦截器
        interceptor= Interceptor(function = { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .addHeader("token",AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    .build()
            chain.proceed(request)
        })
        //初始化一个Retrofit
        retrofit=Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    //初始化一个okhttp实例
    private fun initClient(): OkHttpClient? {
        return OkHttpClient.Builder()
                .addInterceptor(initLogInterceptor())//日志拦截器
                .addInterceptor(interceptor)//header拦截器
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build()

    }

    /**
     * 初始化一个日志拦截器
     */
    private fun initLogInterceptor(): Interceptor? {
        val interceptor=HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 生产方法，暴露一个方法给外界调用生成一哥retrofit实例
     */
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }
}