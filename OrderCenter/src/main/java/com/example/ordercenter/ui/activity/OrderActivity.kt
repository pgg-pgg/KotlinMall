package com.example.ordercenter.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.ordercenter.R
import com.example.ordercenter.common.OrderConstant
import com.example.ordercenter.common.OrderStatus
import com.example.ordercenter.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 * Created by pengganggui on 2018/8/23.
 */
class OrderActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        initView()
    }

    private fun initView() {
        mOrderTab.tabMode=TabLayout.MODE_FIXED
        mOrderVp.adapter=OrderVpAdapter(supportFragmentManager,this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem=intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS,OrderStatus.ORDER_ALL)
    }
}