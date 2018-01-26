package com.xiaoqianghe.koltin.basekoltin.ui.activity

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.orhanobut.logger.Logger
import com.xiaoqianghe.basekoltin.utils.AppUtils
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.showToast
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.view.*
//import me.weyye.hipermission.HiPermission
//import me.weyye.hipermission.PermissionCallback
//import me.weyye.hipermission.PermissionItem


/**
 *
 * Author：Wq
 * Date：2018/1/19 13:51
 * Description：//todo
 *
 *
 */
class SplashActivity : BaseActivity(){
    override fun initStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        layout_splash.startAnimation(alphaAnimation)

//        layout_splash.startAnimation(tranAnimation)
    }

    private var textTypeface: Typeface? =null

    private var descTypeface: Typeface? =null
    private var alphaAnimation:AlphaAnimation?=null

    private var tranAnimation :TranslateAnimation?=null



    init {
//        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
//        descTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }



//
//    override fun initListener() {
//        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun initView() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

//        tv_app_name.typeface = textTypeface
//        tv_splash_desc.typeface = descTypeface
        tv_version_name.text = "v${AppUtils.getVerName(MyApplication.context)}"

        //渐变展示启动屏 nk


        alphaAnimation= AlphaAnimation(0.05f, 1.0f)
        alphaAnimation?.duration = 5000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }

            override fun onAnimationStart(animation: Animation) {

            }

        })

        tranAnimation= TranslateAnimation(0f,10.0f,0.0f,0.0f)
        tranAnimation!!.duration=6000
        tranAnimation!!.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(p0: Animation?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                redirectTo()
            }

            override fun onAnimationStart(p0: Animation?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })


//        checkPermission()


    }


    fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initData() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initLayoutId(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    return R.layout.activity_splash

    }



    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
//    private fun checkPermission(){
//        val permissionItems = ArrayList<PermissionItem>()
//        permissionItems.add(PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone))
//        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,"存储空间",R.drawable.permission_ic_storage))
//        HiPermission.create(this)
//                .title("亲爱的上帝")
//                .msg("为了能够正常使用，请开启这些权限吧！")
//                .permissions(permissionItems)
//                .style(R.style.PermissionDefaultBlueStyle)
//                .animStyle(R.style.PermissionAnimScale)
//                .checkMutiPermission(object : PermissionCallback {
//                    override fun onClose() {
//                        Logger.i( "permission_onClose")
//                        showToast("用户关闭了权限")
//                    }
//
//                    override fun onFinish() {
//                        showToast("初始化完毕！")
//                        layout_splash.startAnimation(alphaAnimation)
//                    }
//
//                    override fun onDeny(permission: String, position: Int) {
//                        Logger.i("permission_onDeny")
//                    }
//
//                    override fun onGuarantee(permission: String, position: Int) {
//                        Logger.i("permission_onGuarantee")
//                    }
//                })
//    }

}