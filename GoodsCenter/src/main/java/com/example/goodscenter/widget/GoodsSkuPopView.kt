package com.example.goodscenter.widget

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.RelativeLayout
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.utils.YuanFenConverter
import com.example.baselibrary.widgets.DefaultTextWatcher
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.data.protocol.GoodsSku
import com.example.goodscenter.event.AddCartEvent
import com.example.goodscenter.event.SkuChangedEvent
import com.kotlin.goods.getEditText
import kotlinx.android.synthetic.main.layout_sku_pop.view.*
import org.jetbrains.anko.find

/**
 * Created by pengganggui on 2018/8/22.
 * 商品SKU弹窗
 */
class GoodsSkuPopView(context: Activity):PopupWindow(context) ,View.OnClickListener{

    private val mRootView:View
    private val mContext:Context
    private val mSkuViewList= arrayListOf<SkuView>()

    init {
        mContext=context
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mRootView=inflater.inflate(R.layout.layout_sku_pop,null)
        initView()

        //设置SelectPicPopupWindow的view
        this.contentView=mRootView
        //设置SelectPicPopupWindow弹出框体的宽
        this.width=ViewGroup.LayoutParams.MATCH_PARENT
        //设置SelectPicPopupWindow弹出框体的高
        this.height=ViewGroup.LayoutParams.MATCH_PARENT
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.isFocusable = true
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.animationStyle = R.style.AnimBottom
        background.alpha = 150
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mRootView.setOnTouchListener { _, event ->
            val height = mRootView.find<RelativeLayout>(R.id.mPopView).top
            val y = event.y.toInt()
            if (event.action == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss()
                }
            }
            true
        }

    }

    private fun initView() {
        mRootView.mCloseIv.onClick(this)
        mRootView.mAddCartBtn.onClick(this)

        mRootView.mSkuCountBtn.setCurrentNumber(1)

        mRootView.mSkuCountBtn.getEditText().addTextChangedListener(
                object : DefaultTextWatcher(){
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        Bus.send(SkuChangedEvent())
                    }
                }

        )

        mRootView.mAddCartBtn.onClick {
            Bus.send(AddCartEvent())
            dismiss()
        }
    }


    /*
       设置商品图标
    */
    fun setGoodsIcon(text: String) {
        mRootView.mGoodsIconIv.loadUrl(this.mContext,text)
    }

    /*
        设置商品价格
     */
    fun setGoodsPrice(text: Long) {
        mRootView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(text)
    }

    /*
        设置商品编号
     */
    fun setGoodsCode(text: String) {
        mRootView.mGoodsCodeTv.text = "商品编号:" + text
    }

    /*
        设置商品SKU
     */
    fun setSkuData(list: List<GoodsSku>) {
        for (goodSku in list) {
            val skuView = SkuView(mContext)
            skuView.setSkuData(goodSku)

            mSkuViewList.add(skuView)
            mRootView.mSkuView.addView(skuView)
        }
    }

    /*
        获取选中的SKU
     */
    fun getSelectSku(): String {
        var skuInfo = ""
        for (skuView in mSkuViewList) {
            skuInfo += skuView.getSkuInfo().split(GoodsConstant.SKU_SEPARATOR)[1] + GoodsConstant.SKU_SEPARATOR
        }
        return skuInfo.take(skuInfo.length - 1)//刪除最后一个分隔
    }

    /*
        获取商品数量
     */
    fun getSelectCount() = mRootView.mSkuCountBtn.number

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mCloseIv -> dismiss()
            R.id.mAddCartBtn -> {
                dismiss()
            }
        }
    }
}