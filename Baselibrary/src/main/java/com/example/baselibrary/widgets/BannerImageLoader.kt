package com.example.baselibrary.widgets

import android.content.Context
import android.widget.ImageView
import com.example.baselibrary.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Created by pengganggui on 2018/8/21.
 * 图片加载器
 */
class BannerImageLoader:ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context,path.toString(),imageView)
    }
}