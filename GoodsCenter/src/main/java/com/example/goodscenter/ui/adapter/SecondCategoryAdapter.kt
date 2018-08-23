package com.example.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.goodscenter.R
import com.example.goodscenter.data.protocol.Category
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * Created by pengganggui on 2018/8/22.
 */
class SecondCategoryAdapter(context:Context):BaseRecyclerViewAdapter<Category,SecondCategoryAdapter.ViewHolder>(context) {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_second_category_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model=dataList[position]
        //分类图片
        holder.itemView.mCategoryIconIv.loadUrl(mContext,model.categoryIcon)
        //分类名称
        holder.itemView.mSecondCategoryNameTv.text=model.categoryName
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view)
}