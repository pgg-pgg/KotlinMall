package com.example.baselibrary.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.example.baselibrary.R

/**
 * Created by pengganggui on 2018/8/21.
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val mCartBadge:TextBadgeItem
    private val mMsgBadge:ShapeBadgeItem
    init {
        //首页底部item
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setInActiveColorResource(R.color.text_normal)
                .setInActiveColorResource(R.color.common_blue)

        //分类底部item
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press, resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setInActiveColorResource(R.color.text_normal)
                .setInActiveColorResource(R.color.common_blue)

        //购物车底部item
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press, resources.getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setInActiveColorResource(R.color.text_normal)
                .setInActiveColorResource(R.color.common_blue)

        mCartBadge= TextBadgeItem()
        cartItem.setBadgeItem(mCartBadge)
        mCartBadge.setText("10")

        //消息底部item
        val messageItem = BottomNavigationItem(R.drawable.btn_nav_msg_press, resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setInActiveColorResource(R.color.text_normal)
                .setInActiveColorResource(R.color.common_blue)

        mMsgBadge= ShapeBadgeItem()
        mMsgBadge.setShape(ShapeBadgeItem.SHAPE_OVAL)
        messageItem.setBadgeItem(mMsgBadge)

        //我的底部item
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press, resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setInActiveColorResource(R.color.text_normal)
                .setInActiveColorResource(R.color.common_blue)

        //固定再下方
        setMode(BottomNavigationBar.MODE_FIXED)
        //背景样式
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
        setBarBackgroundColor(R.color.common_white)

        //添加item
        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(messageItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }


    fun checkCartBadge(count:Int){
        if(count==0){
            mCartBadge.hide()
        }else{
            mCartBadge.show()
            mCartBadge.setText("$count")
        }
    }

    fun checkMsgBadge(isVisible:Boolean){
        if(isVisible){
            mMsgBadge.show()
        }else{
            mMsgBadge.hide()
        }
    }

}