package com.example.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.baselibrary.utils.YuanFenConverter
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.data.protocol.CartGoods
import com.example.goodscenter.event.CartAllCheckedEvent
import com.example.goodscenter.event.UpdateCartSizeEvent
import com.example.goodscenter.event.UpdateTotalPriceEvent
import com.example.goodscenter.injection.component.DaggerCartComponent
import com.example.goodscenter.injection.module.CartModule
import com.example.goodscenter.presenter.CartPresenter
import com.example.goodscenter.presenter.view.CartView
import com.example.goodscenter.ui.adapter.CartGoodsAdapter
import com.example.provider.common.ProviderConstant
import com.example.provider.router.RouterPath
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by pengganggui on 2018/8/23.
 * 购物车Fragment
 */
class CartFragment :BaseMvpFragment<CartPresenter>(),CartView {

    private lateinit var mAdapter:CartGoodsAdapter

    private var mTotalPrice: Long = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }


    /**
     * 初始化视图
     */
    private fun initView() {
        mCartGoodsRv.layoutManager=LinearLayoutManager(context)
        mAdapter= CartGoodsAdapter(context)
        mCartGoodsRv.adapter=mAdapter
        //编辑按钮
        mHeaderBar.getRightView().onClick{
            refreshEditStatus()
        }

        //全选按钮事件
        mAllCheckedCb.onClick{
            for (item in mAdapter.dataList){
                item.isSelected=mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        //删除按钮
        mDeleteBtn.onClick{
            val cartIdList:MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }.mapTo(cartIdList){it.id}
            if (cartIdList.size==0){
                toast("请选择需要删除的数据")
            }else{
                mPresenter.deleteCartList(cartIdList)
            }
        }

        //结算按钮事件
        mSettleAccountsBtn.onClick{
            val cartGoodsList:MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }.mapTo(cartGoodsList){it}
            if (cartGoodsList.size==0){
                toast("请选择需要提交的数据")
            }else{
                mPresenter.submitCart(cartGoodsList,mTotalPrice)
            }
        }
    }

    /**
     * 更新总价
     */
    private fun updateTotalPrice() {
        mTotalPrice=mAdapter.dataList.filter { it.isSelected }.map { it.goodsCount*it.goodsPrice }.sum()
        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    /**
     * 刷新是否为编辑状态
     */
    private fun refreshEditStatus() {
        val isEditStatus=getString(R.string.common_edit)==mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text=if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)
    }


    /**
     * 注册监听
     */
    private fun initObserve() {
        //全选的状态
        Bus.observe<CartAllCheckedEvent>().subscribe{t:CartAllCheckedEvent ->
            run{
                mAllCheckedCb.isChecked=t.isAllChecked
                updateTotalPrice()
            }
        }.registerInBus(this)
        //更新总价
        Bus.observe<UpdateTotalPriceEvent>().subscribe{
            updateTotalPrice()
        }.registerInBus(this)
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    /**
     * 获取购物车列表回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result!=null&&result.size>0){
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked=false
            mMultiStateView.viewState=MultiStateView.VIEW_STATE_CONTENT
        }else{
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState=MultiStateView.VIEW_STATE_EMPTY
        }

        //本地存储并发送事件刷新UI
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,result?.size?:0)
        Bus.send(UpdateCartSizeEvent())
        //更新总价
        updateTotalPrice()
    }

    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()
    }

    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID,result)
                .navigation()
    }

    /**
     * Dagger注册
     */
    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent).cartModule(CartModule()).build().inject(this)
        mPresenter.mView=this
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
           设置Back是否可见
        */
    fun setBackVisible(isVisible:Boolean){
        mHeaderBar.getLeftView().setVisible(isVisible)
    }
}