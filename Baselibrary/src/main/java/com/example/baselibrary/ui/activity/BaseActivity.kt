package com.example.baselibrary.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import com.example.baselibrary.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import org.jetbrains.anko.find

/**
 * Created by pengganggui on 2018/8/14.
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    val contentView: View
        get() {
            val content=find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }

}