package com.example.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.YuanFenConverter
import com.example.ordercenter.R
import com.example.ordercenter.data.protocol.Order
import com.example.ordercenter.injection.component.DaggerOrderComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.presenter.OrderDetailPresenter
import com.example.ordercenter.presenter.view.OrderDetailView
import com.example.ordercenter.ui.adapter.OrderGoodsAdapter
import com.example.provider.common.ProviderConstant
import com.example.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 * Created by pengganggui on 2018/8/24.
 */

@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
class OrderDetailActivity:BaseMvpActivity<OrderDetailPresenter>(),OrderDetailView {
    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
        initData()
    }



    /**
     * 初始化视图
     */
    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,-1))
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView=this
    }

    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))

        mAdapter.setData(result.orderGoodsList)
    }
}