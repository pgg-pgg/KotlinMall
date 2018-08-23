package com.example.pengganggui.kotlinmall.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.goodscenter.common.GoodsConstant
import com.example.goodscenter.event.UpdateCartSizeEvent
import com.example.goodscenter.ui.fragment.CartFragment
import com.example.goodscenter.ui.fragment.CategoryFragment
import com.example.pengganggui.kotlinmall.R
import com.example.pengganggui.kotlinmall.ui.fragment.HomeFragment
import com.example.pengganggui.kotlinmall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mStack=Stack<Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initBottomNav()
        changeFragment(0)

        initObserve()
        loadCartSize()
    }

    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>().subscribe(){
            loadCartSize()
        }.registerInBus(this)

    }

    private fun initFragment() {
        val manager=supportFragmentManager.beginTransaction()
        manager.add(R.id.mContainer,mHomeFragment)
        manager.add(R.id.mContainer,mCategoryFragment)
        manager.add(R.id.mContainer,mCartFragment)
        manager.add(R.id.mContainer,mMsgFragment)
        manager.add(R.id.mContainer,mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }



    private fun initBottomNav(){
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }

    /**
     * 底部栏切换事件
     */
    private fun changeFragment(position: Int) {
        val manager=supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()
    }
}
