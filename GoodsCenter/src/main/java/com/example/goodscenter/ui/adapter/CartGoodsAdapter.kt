package com.example.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.baselibrary.utils.YuanFenConverter
import com.example.baselibrary.widgets.DefaultTextWatcher
import com.example.goodscenter.R
import com.example.goodscenter.data.protocol.CartGoods
import com.example.goodscenter.event.CartAllCheckedEvent
import com.example.goodscenter.event.UpdateTotalPriceEvent
import com.kotlin.goods.getEditText
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

/**
 * Created by pengganggui on 2018/8/23.
 */
class CartGoodsAdapter(context:Context):BaseRecyclerViewAdapter<CartGoods,CartGoodsAdapter.ViewHolder>(context){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(mContext).inflate(R.layout.layout_cart_goods_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model=dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(mContext,model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)
        //选中按钮事件
        holder.itemView.mCheckedCb.onClick{
            model.isSelected=holder.itemView.mCheckedCb.isChecked
            val isAllChecked=dataList.all { it.isSelected }
            Bus.send(CartAllCheckedEvent(isAllChecked))
            notifyDataSetChanged()
        }

        //商品数量变化监听
        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object :DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                model.goodsCount=s.toString().toInt()
                Bus.send(UpdateTotalPriceEvent())
            }
        })
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view)
}