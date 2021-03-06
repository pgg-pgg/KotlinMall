package com.example.goodscenter.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.baselibrary.utils.YuanFenConverter
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.data.protocol.Goods
import com.example.goodscenter.event.AddCartEvent
import com.example.goodscenter.event.GoodsDetailImageEvent
import com.example.goodscenter.event.SkuChangedEvent
import com.example.goodscenter.event.UpdateCartSizeEvent
import com.example.goodscenter.injection.component.DaggerGoodsComponent
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.presenter.GoodsDetailPresenter
import com.example.goodscenter.presenter.view.GoodsDetailView
import com.example.goodscenter.ui.activity.GoodsDetailActivity
import com.example.goodscenter.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Created by pengganggui on 2018/8/22.
 * 商品详情页第一页
 */
class GoodsDetailTabOneFragment:BaseMvpFragment<GoodsDetailPresenter>(),GoodsDetailView {

    //SKU弹窗页
    private lateinit var mSkuPop:GoodsSkuPopView

    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods:Goods? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one,container,false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        mPresenter.getGoodsDetailList(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID,-1))
    }


    /**
     * 监听SKU变化以及加入购物车事件
     */
    private fun initObserve() {
        //监听SKU变化
        Bus.observe<SkuChangedEvent>()
                .subscribe{
                    mSkuSelectedTv.text=mSkuPop.getSelectSku()+GoodsConstant.SKU_SEPARATOR+mSkuPop.getSelectCount()+"件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe{
                    addCart()
                }.registerInBus(this)
    }

    private fun addCart() {
        mCurGoods?.let {
            mPresenter.addCart(it.id,it.goodsDesc,it.goodsDefaultIcon,it.goodsDefaultPrice,mSkuPop.getSelectCount(),mSkuPop.getSelectSku())
        }
    }

    /**
     * 加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPop.setSkuData(result.goodsSku)
    }

    /**
     * 初始化弹窗的动画
     */
    private fun initAnim() {
        //弹窗出现动画
        mAnimationStart=ScaleAnimation(1f,0.95f,1f,0.95f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        mAnimationStart.duration=500
        mAnimationStart.fillAfter=true

        //弹窗消失动画
        mAnimationEnd=ScaleAnimation(0.95f,1f,0.95f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        mAnimationEnd.duration=500
        mAnimationEnd.fillAfter=true
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        //初始化最上方轮播图
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //Sku弹层点击触发事件
        mSkuView.onClick{
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView,Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,0,0)
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }


    /**
     * 初始化SKU弹窗
     */
    private fun initSkuPop() {
        mSkuPop= GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener{
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }



    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView=this
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods=result
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text=result.goodsDesc

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))
        loadPopData(result)
    }

    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}