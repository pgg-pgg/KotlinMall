package com.example.pengganggui.kotlinmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.pengganggui.kotlinmall.R
import com.example.pengganggui.kotlinmall.common.*
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by pengganggui on 2018/8/21.
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initNews()
    }

    /**
     * 初始化新闻模块
     */
    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场","新用户立领1000元优惠券"))
    }

    /**
     * 初始化Banner
     */
    private fun initView() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR, HOME_DISCOUNT_FIVE))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        //设置指示器位置
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner全部设置完毕后调用
        mHomeBanner.start()
    }
}