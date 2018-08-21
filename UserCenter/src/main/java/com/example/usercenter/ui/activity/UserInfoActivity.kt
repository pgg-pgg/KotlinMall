package com.example.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.baselibrary.utils.DateUtils
import com.example.baselibrary.utils.GlideUtils
import com.example.provider.common.ProviderConstant
import com.example.usercenter.R
import com.example.usercenter.data.protocol.UserInfo
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.UserInfoPresenter
import com.example.usercenter.presenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.user.utils.UserPrefsUtils
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import java.io.File


/**
 * Created by pengganggui on 2018/8/21.
 * 用户信息界面
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener, TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl: String? = null

    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null
    private var mUserMobile: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        initView()
        initData()
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mRemoteFileUrl = mUserIcon
        mUserNameEt.setText(mUserName)
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }
        mUserMobileTv.text = mUserMobile
        mUserSignEt.setText(mUserSign)
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mUserIconView.onClick(this)
        mHeaderBar.getRightView().onClick(this)
    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.mUserIconView -> {
                showAlertView()
            }
            R.id.mRightTv -> {
                mPresenter.editUser(mRemoteFileUrl!!, mUserNameEt.text?.toString()
                        ?: "", if (mGenderMaleRb.isChecked) "0" else "1",
                        mUserSignEt.text?.toString() ?: "")
            }
        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this, AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)//压缩图片
                when (position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }
        }).show()
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
        toast("取消")
    }

    override fun takeFail(result: TResult?, msg: String?) {
        toast("获取失败")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    private fun createTempFile() {
        //文件名
        val tempFileName = "${DateUtils.curTime}.png"
        //sd卡是否可用
        if (Environment.MEDIA_MOUNTED == (Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        this.mTempFile = File(filesDir, tempFileName)
    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, { key, info, response ->
            mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response!!.get("hash")
            GlideUtils.loadImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
        }, null)
    }

    override fun onEditUserResult(userInfo: UserInfo) {
        toast("修改成功")
        //保存信息
        UserPrefsUtils.putUserInfo(userInfo)
    }
}