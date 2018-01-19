package com.xiaoqianghe.koltin.basekoltin.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.classic.common.MultipleStatusView
import com.xiaoqianghe.koltin.basekoltin.MyApplication

/**
 *
 * Author：Wq
 * Date：2017/12/20 14:58
 * Description：//todo
 *
 *
 */
abstract class BaseActivity : AppCompatActivity(){

    //多种状态的View 的切换

    protected  var mLayoutStatusView : MultipleStatusView? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(initLayoutId())
        initData()
        initView()

        initStart()



        initListener()




    }


    abstract fun initStart()

//    abstract fun initListener()

    abstract fun initView()

    abstract fun initData()

    abstract fun start()

    abstract fun initLayoutId(): Int


    private fun  initListener(){

        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }


    open  val mRetryClickListener : View.OnClickListener=View.OnClickListener{
        start()
    }

    override fun onDestroy() {
        super.onDestroy()

        MyApplication.getRefWatcher(this)?.watch(this)
    }


    //打开 软键盘
    fun  openKeyBord(mEditText: EditText, mContext: Context){


        val imm=mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as  InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }


    //关闭软键盘
    fun  closeKeyBord(mEditText: EditText, mContext: Context){

        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)


    }


}