package com.example.ordercenter.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.YuanFenConverter
import com.example.ordercenter.R
import com.example.ordercenter.data.protocol.Order
import com.example.ordercenter.event.SelectAddressEvent
import com.example.ordercenter.injection.component.DaggerOrderComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.presenter.OrderComfirmPresenter
import com.example.ordercenter.presenter.view.OrderConfirmView
import com.example.ordercenter.ui.adapter.OrderGoodsAdapter
import com.example.provider.common.ProviderConstant
import com.example.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by pengganggui on 2018/8/23.
 */

@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity :BaseMvpActivity<OrderComfirmPresenter>(),OrderConfirmView{

    @Autowired(name=ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int=0


    private var mOrder:Order?=null

    private lateinit var mAdapter:OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        initObserve()
        loadData()
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>().subscribe{
            t :SelectAddressEvent ->
            run {
                mOrder?.let {
                    it.shipAddress=t.address
                }
                updateAddressView()
            }
        }.registerInBus(this)
    }

    /**
     * 更新显示地址View
     */
    @SuppressLint("SetTextI18n")
    private fun updateAddressView() {
        mOrder?.let {
            if (it.shipAddress==null){
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            }else{
                mShipView.setVisible(true)
                mSelectShipTv.setVisible(false)
                mShipNameTv.text=it.shipAddress!!.shipUserName+" "+it.shipAddress!!.shipUserMobile
                mShipAddressTv.text=it.shipAddress!!.shipAddress
            }
        }
    }


    private fun loadData() {

    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mPresenter.getOrderById(mOrderId)
        mOrderGoodsRv.layoutManager=LinearLayoutManager(this)
        mAdapter= OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter=mAdapter
        mSelectShipTv.onClick{
            startActivity<ShipAddressActivity>()
        }

        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }

        mSubmitOrderBtn.onClick{
            mOrder?.let {
                mPresenter.submitOrder(it)
            }
        }

    }


    override fun onGetOrderByIdResult(result: Order) {
        mOrder=result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text="合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"

        updateAddressView()
    }

    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交成功")
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(activityComponent).orderModule(OrderModule()).build().inject(this)
        mPresenter.mView=this
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}