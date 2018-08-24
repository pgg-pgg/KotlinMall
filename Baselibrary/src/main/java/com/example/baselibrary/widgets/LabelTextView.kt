package com.example.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.example.baselibrary.R
import kotlinx.android.synthetic.main.layout_label_textview.view.*

/**
 * Created by pengganggui on 2018/8/24.
 */
class LabelTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mLabelText:CharSequence?=null
    private var mContentText:CharSequence?=null

    init {
        val typeArray=context.obtainStyledAttributes(attrs, R.styleable.LabelText)
        this.mLabelText=typeArray.getText(R.styleable.LabelText_labelText)
        this.mContentText=typeArray.getText(R.styleable.LabelText_contentText)
        initView()
        typeArray.recycle()
    }

    private fun initView() {
        View.inflate(context,R.layout.layout_label_textview,this)

        mLabelText?.let{
            mLabelTv.text=it
        }

        mContentText?.let {
            mContentTv.text=it
        }
    }

    /*
      设置内容文本
   */
    fun setContentText(text:String){
        mContentTv.text = text
    }
}