package com.example.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.goodscenter.R
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.ui.adapter.SearchHistoryAdapter
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by pengganggui on 2018/8/22.
 */
class SearchGoodsActivity:BaseActivity(),View.OnClickListener {

    private lateinit var mAdapter:SearchHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_goods)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        val set = AppPrefsUtils.getStringSet(GoodsConstant.SP_SEARCH_HISTORY)
        mNoDataTv.setVisible(set.isEmpty())
        mDataView.setVisible(set.isNotEmpty())
        if (set.isNotEmpty()) {
            val list = mutableListOf<String>()
            list.addAll(set)
            mAdapter.setData(list)
        }
    }

    private fun initView() {
        mLeftIv.onClick(this)
        mSearchTv.onClick(this)
        mClearBtn.onClick(this)
        //RecyclerView适配器
        mAdapter = SearchHistoryAdapter(this)
        mSearchHistoryRv.layoutManager = LinearLayoutManager(this)
        mSearchHistoryRv.adapter = mAdapter
        //item点击事件
        mAdapter.mItemClickListener = object : BaseRecyclerViewAdapter.OnItemClickListener<String> {
            override fun onItemClick(item: String, position: Int) {
                enterGoodsList(item)
            }
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mLeftIv -> finish()
        //执行搜索
            R.id.mSearchTv -> doSearch()
        //清除历史记录
            R.id.mClearBtn -> {
                AppPrefsUtils.remove(GoodsConstant.SP_SEARCH_HISTORY)
                loadData()
            }
        }
    }

    //搜索
    private fun doSearch() {
        if (mKeywordEt.text.isNullOrEmpty()) {
            toast("请输入需要搜索的关键字")
        } else {
            val inputValue = mKeywordEt.text.toString()
            AppPrefsUtils.putStringSet(GoodsConstant.SP_SEARCH_HISTORY, mutableSetOf(inputValue))
            enterGoodsList(inputValue)
        }
    }

    /*
        进入商品列表界面
     */
    private fun enterGoodsList(value: String) {
        //输入不为空，进入商品列表
        startActivity<GoodsActivity>(
                GoodsConstant.KEY_SEARCH_GOODS_TYPE to GoodsConstant.SEARCH_GOODS_TYPE_KEYWORD,
                GoodsConstant.KEY_GOODS_KEYWORD to value
        )
    }
}