package com.example.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.baselibrary.utils.YuanFenConverter
import com.example.goodscenter.R
import com.example.goodscenter.data.protocol.Goods
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/**
 * Created by pengganggui on 2018/8/22.
 */
class GoodsAdapter(context: Context):BaseRecyclerViewAdapter<Goods,GoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(mContext).inflate(R.layout.layout_goods_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model=dataList[position]
        holder.itemView.mGoodsIconIv.loadUrl(mContext,model.goodsDefaultIcon)
        holder.itemView.mGoodsDescTv.text=model.goodsDesc
        holder.itemView.mGoodsPriceTv.text=YuanFenConverter.changeF2YWithUnit(model.goodsDefaultPrice)
        holder.itemView.mGoodsSalesStockTv.text="销量${model.goodsSalesCount}件     库存${model.goodsStockCount}"
    }

    class ViewHolder(view:View) :RecyclerView.ViewHolder(view)
}