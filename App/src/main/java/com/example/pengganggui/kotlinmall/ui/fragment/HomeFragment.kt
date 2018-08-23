package com.example.pengganggui.kotlinmall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.goodscenter.ui.activity.SearchGoodsActivity
import com.example.pengganggui.kotlinmall.R
import com.example.pengganggui.kotlinmall.common.*
import com.example.pengganggui.kotlinmall.ui.adapter.HomeDisCountAdapter
import com.example.pengganggui.kotlinmall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

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
        mSearchEt.onClick{
            startActivity<SearchGoodsActivity>()
        }
        initView()
        initNews()
        initDisCount()
        initTopic()
    }

    /**
     * 初始化新闻模块
     */
    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
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

    /**
     * 初始化折扣
     */
    private fun initDisCount() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager
        val disCountAdapter = HomeDisCountAdapter(activity)
        mHomeDiscountRv.adapter = disCountAdapter
        disCountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR
                , HOME_DISCOUNT_FIVE))
    }

    /**
     * 初始化话题banner
     */
    private fun initTopic() {
        mTopicPager.adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR,
                HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5
        CoverFlow.Builder()
                .with(mTopicPager)
                .scale(0.3f)
                .pagerMargin(-30.0f)
                .spaceSize(0.0f)
                .build()

    }
}