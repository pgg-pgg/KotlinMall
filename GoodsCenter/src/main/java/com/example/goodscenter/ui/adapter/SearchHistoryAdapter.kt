package com.example.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.goodscenter.R
import kotlinx.android.synthetic.main.layout_search_history_item.view.*

/**
 * Created by pengganggui on 2018/8/22.
 */
class SearchHistoryAdapter(context:Context):BaseRecyclerViewAdapter<String, SearchHistoryAdapter.ViewHolder>(context){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_search_history_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mSearchHistoryTv.text=dataList[position]
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view)
}