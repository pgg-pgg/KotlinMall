package com.example.pengganggui.kotlinmall.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.baselibrary.utils.GlideUtils
import com.example.pengganggui.kotlinmall.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * Created by pengganggui on 2018/8/22.
 */
class HomeDisCountAdapter(context:Context) :BaseRecyclerViewAdapter<String,HomeDisCountAdapter.ViewHolder>(context)  {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadUrlImage(mContext,dataList[position],holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text="￥23.00"
        holder.itemView.mDiscountBeforeTv.text="￥100.00"
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        init {
            view.mDiscountBeforeTv.paint.flags=Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias=true
        }
    }
}