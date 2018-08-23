package com.example.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.data.protocol.Goods
import com.example.goodscenter.injection.component.DaggerGoodsComponent
import com.example.goodscenter.injection.module.GoodsModule
import com.example.goodscenter.presenter.GoodsPresenter
import com.example.goodscenter.presenter.view.GoodsView
import com.example.goodscenter.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity

/**
 * Created by pengganggui on 2018/8/22.
 */

class GoodsActivity :BaseMvpActivity<GoodsPresenter>(),GoodsView,BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var goodsAdapter:GoodsAdapter
    private var mCurrentPage:Int=1
    private var mMaxPage:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    /**
     * 初始化下拉刷新
     */
    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder=BGANormalRefreshViewHolder(this,true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mGoodsRv.layoutManager=GridLayoutManager(this,2)
        goodsAdapter= GoodsAdapter(this)
        mGoodsRv.adapter=goodsAdapter
        goodsAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Goods>{
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })
    }

    private fun loadData(){
        if(intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE,0)!=0){
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD),mCurrentPage)
        }else{
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID,1),mCurrentPage)
        }

    }
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView=this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result!=null&&result.size>0){
            mMaxPage=result[0].maxPage
            if (mCurrentPage==1){
                goodsAdapter.setData(result)
            }else{
                goodsAdapter.dataList.addAll(result)
                goodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState=MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState=MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (mCurrentPage<mMaxPage){
            mCurrentPage++
            loadData()
            true
        }else{
            false
        }

    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage=1
        loadData()
    }
}