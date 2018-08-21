package com.example.pengganggui.kotlinmall.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pengganggui.kotlinmall.R
import com.example.pengganggui.kotlinmall.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val manager=supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContainer,HomeFragment())
        manager.commit()
    }
}
