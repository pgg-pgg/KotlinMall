package com.example.pengganggui.kotlinmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.ordercenter.common.OrderConstant
import com.example.ordercenter.common.OrderStatus
import com.example.ordercenter.ui.activity.OrderActivity
import com.example.ordercenter.ui.activity.ShipAddressActivity
import com.example.pengganggui.kotlinmall.R
import com.example.pengganggui.kotlinmall.ui.activity.SettingActivity
import com.example.provider.common.ProviderConstant
import com.example.provider.common.afterLogin
import com.example.provider.common.isLogined
import com.example.usercenter.ui.activity.LoginActivity
import com.example.usercenter.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * Created by pengganggui on 2018/8/21.
 */
class MeFragment : BaseFragment() ,View.OnClickListener{

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mAddressTv.onClick(this)
        mAllOrderTv.onClick(this)
        mSettingTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mWaitPayOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if(!isLogined()){
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text=getString(R.string.un_login_text)
        }else{
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()){
                mUserIconIv.loadUrl(this.activity,userIcon)
            }
            mUserNameTv.text=AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.mUserIconIv,R.id.mUserNameTv ->{
               afterLogin {
                   startActivity<UserInfoActivity>()
               }
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }

            R.id.mAddressTv -> {
                startActivity<ShipAddressActivity>()
            }

            R.id.mAllOrderTv ->{
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }

            R.id.mWaitConfirmOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }

            R.id.mWaitPayOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
                }
            }

            R.id.mCompleteOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
                }
            }
        }
    }
}