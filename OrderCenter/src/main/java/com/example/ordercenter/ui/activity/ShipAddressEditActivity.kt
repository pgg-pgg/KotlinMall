package com.example.ordercenter.ui.activity

import android.os.Bundle
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.ordercenter.R
import com.example.ordercenter.common.OrderConstant
import com.example.ordercenter.data.protocol.ShipAddress
import com.example.ordercenter.injection.component.DaggerShipAddressComponent
import com.example.ordercenter.injection.module.ShipAddressModule
import com.example.ordercenter.presenter.EditShipAddressPresenter
import com.example.ordercenter.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * Created by pengganggui on 2018/8/23.
 */
class ShipAddressEditActivity: BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {
    private var mAddress: ShipAddress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }

    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    private fun initView() {
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()){
                toast("名称不能为空")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()){
                toast("电话不能为空")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()){
                toast("地址不能为空")
                return@onClick
            }
            if (mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(),
                        mShipAddressEt.text.toString())
            }else{
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功")
        finish()
    }

    override fun onEditShipAddressResult(result: Boolean) {
        toast("修改地址成功")
        finish()
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(activityComponent).shipAddressModule(ShipAddressModule()).build().inject(this)
        mPresenter.mView=this
    }
}