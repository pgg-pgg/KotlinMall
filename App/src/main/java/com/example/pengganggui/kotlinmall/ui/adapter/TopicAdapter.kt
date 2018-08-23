package com.example.pengganggui.kotlinmall.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ext.loadUrl
import com.example.pengganggui.kotlinmall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * Created by pengganggui on 2018/8/22.
 */
class TopicAdapter(private val context: Context,private val list:List<String>):PagerAdapter() {

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return this.list.size
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView=LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item,null)
        rootView.mTopicIv.loadUrl(context,list[position])
        container.addView(rootView)
        return rootView
    }
}