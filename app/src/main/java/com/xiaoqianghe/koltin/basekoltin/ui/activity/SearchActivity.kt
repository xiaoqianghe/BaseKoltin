package com.xiaoqianghe.koltin.basekoltin.ui.activity

import android.annotation.TargetApi
import android.graphics.Typeface
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.android.flexbox.*
import com.xiaoqianghe.basekoltin.net.exception.ErrorStatus
import com.xiaoqianghe.basekoltin.utils.StatusBarUtil
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.SearchContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.SearchPresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.CategoryDetailAdapter
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.HotKeywordsAdapter
import com.xiaoqianghe.koltin.basekoltin.view.ViewAnimUtils.animateRevealShow
import kotlinx.android.synthetic.main.activity_search.*


/**
 *
 * Author：Wq
 * Date：2018/1/19 15:06
 * Description：//todo
 *
 *
 */
class SearchActivity :BaseActivity(),SearchContract.View{
    override fun showError(errorMsg: String, errorCode: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }

    }

    override fun start() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        //请求    热门关键字
        mPresenter.requestHotWordData()
    }


    private val mPresenter by lazy {
        SearchPresenter()
    }

    private val mResultAdapter by lazy {

        CategoryDetailAdapter(this,itemList, R.layout.item_category_detail)
    }

    private var itemList =ArrayList<HomeBean.Issue.Item>()
    private var  keyWords : String? =null;
    private var mTextTypeface : Typeface? =null

    private var mHotKeywordsAdapter : HotKeywordsAdapter? =null



    private var loadingMore =false;

    init{
        mPresenter.attachView(this)

        //细黑简体字体
        mTextTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }


    override fun showLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mLayoutStatusView?.showLoading()

    }

    override fun dismissLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mLayoutStatusView?.showContent()
    }

    override fun setHotWordData(string: ArrayList<String>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        showHotWordView()
        mHotKeywordsAdapter = HotKeywordsAdapter(this, string, R.layout.item_flow_text)

        val flexBoxLayoutManager = FlexboxLayoutManager(this)
        flexBoxLayoutManager.flexWrap = FlexWrap.WRAP      //按正常方向换行
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW   //主轴为水平方向，起点在左端
        flexBoxLayoutManager.alignItems = AlignItems.CENTER    //定义项目在副轴轴上如何对齐
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START  //多个轴对齐方式

        mRecyclerView_hot.layoutManager = flexBoxLayoutManager
        mRecyclerView_hot.adapter = mHotKeywordsAdapter
        //设置 Tag 的点击事件
        mHotKeywordsAdapter?.setOnTagItemClickListener {
            closeSoftKeyboard()
            keyWords = it
            mPresenter.querySearchData(it)
        }


//        mPresenter.querySearchData()

    }

    /**
     * 显示热门关键字的 流式布局
     */
    private fun showHotWordView(){
        layout_hot_words.visibility = View.VISIBLE
        layout_content_result.visibility = View.GONE
    }


    override fun setSearchResult(issue: HomeBean.Issue) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun initListener() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun closeSoftKeyboard() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        closeKeyBord(et_search_view,applicationContext)
    }



    override fun initView() {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        tv_title_tip.typeface=mTextTypeface
        tv_hot_search_words.typeface=mTextTypeface

        //初始化查询结果

        mRecyclerView_result.layoutManager=LinearLayoutManager(this)

        mRecyclerView_result.adapter=mResultAdapter

        //实现自动加载

        mRecyclerView_result.addOnScrollListener(object :RecyclerView.OnScrollListener(){



            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val itemCount =mRecyclerView_result.layoutManager.itemCount
                val lastVisibleItem = (mRecyclerView_result.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })

        //取消

        tv_cancel.setOnClickListener { onBackPressed() }

        //键盘的搜索按钮
        et_search_view.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    closeSoftKeyboard()
                    keyWords = et_search_view.text.toString().trim()
                    if (keyWords.isNullOrEmpty()) {
                        showToast("请输入你感兴趣的关键词")
                    } else {
                        mPresenter.querySearchData(keyWords!!)
                    }
                }
                return false
            }

        })


        mLayoutStatusView=multipleStatusView

        //设置状态栏透明和间距处理

        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)

    }

    override fun initData() {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setUpEnterAnimation() // 入场动画
            setUpExitAnimation() // 退场动画
        } else {
            setUpView()
        }


    }




    private fun setUpView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpExitAnimation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * 进场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpEnterAnimation() {
        val transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {

            }

            override fun onTransitionEnd(transition: Transition) {
//                transition.removeListener(this)
//                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
    }

    override fun setEmptyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initLayoutId(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return R.layout.activity_search
    }


}