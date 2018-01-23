package com.xiaoqianghe.koltin.basekoltin.ui.activity

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.transition.Transition
import android.view.View
import android.widget.ImageView
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.header.MaterialHeader
import com.shuyu.gsyvideoplayer.listener.LockClickListener
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.xiaoqianghe.basekoltin.utils.StatusBarUtil
import com.xiaoqianghe.basekoltin.utils.WatchHistoryUtils
import com.xiaoqianghe.koltin.basekoltin.Constants
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.mvp.contract.VideoDetailContract
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.mvp.presenter.VideoDetailPresenter
import com.xiaoqianghe.koltin.basekoltin.showToast
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.VideoDetailAdapter
import com.xiaoqianghe.koltin.basekoltin.utils.GlideUtils
import com.xiaoqianghe.koltin.basekoltin.view.VideoListener
import kotlinx.android.synthetic.main.activity_video_detail.*
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * Author：Wq
 * Date：2017/12/25 15:38
 * Description：//todo
 *
 *
 */
class VideoDetailActivity :BaseActivity(),VideoDetailContract.View {
    override fun start() {
    //    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val IMG_TRANSITION = "IMG_TRANSITION"
        val TRANSITION = "TRANSITION"
    }



    private val mPresenter by lazy {

        VideoDetailPresenter()
    }

    private val mAdapter by lazy {

        VideoDetailAdapter(this,itemList)


    }


    private var itemList = java.util.ArrayList<HomeBean.Issue.Item>()

    private val mFormat by lazy {
        SimpleDateFormat("yyyyMMddHHmmss")
    }



    /**
     * Item 详细数据
     */
    private lateinit var itemData: HomeBean.Issue.Item
    private var orientationUtils: OrientationUtils? = null
    private var isPlay: Boolean = false
    private var isPause: Boolean = false
    private var isTransition: Boolean = false
    private var transition: Transition? = null
    private var mMaterialHeader: MaterialHeader? = null




    override fun setVideo(url: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        Logger.d("playUrl:$url")

        mVideoView.setUp(url,false,"")

        //开始自动播放
        mVideoView.startPlayLogic()
    }

    override fun setVideoInfo(itemInfo: HomeBean.Issue.Item) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        itemData=itemInfo
        mAdapter.addData(itemInfo)

        //请求相关的最新的视频
        mPresenter.requestRelateVideo(itemInfo.data?.id?:0)

    }

    override fun setBackground(url: String) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        GlideUtils.load(this,url,mVideoBackground)

    }

    override fun initStart() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        mAdapter.addData(itemList)

        this.itemList=itemList

    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        if(isPlay&&!isPause){

            mVideoView.onConfigurationChanged(this,newConfig,orientationUtils)
        }
    }

//    override fun initListener() {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mPresenter.attachView(this)

        initTransition()

        initVideoViewConfig()


        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

        //设置相关视频 Item 的点击事件
        mAdapter.setOnItemDetailClick { mPresenter.loadVideoInfo(it) }

        //状态栏透明和间距处理
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, mVideoView)

        /***  下拉刷新  ***/
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            loadVideoInfo()
        }
        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader?
        //打开下拉刷新区域块背景:
        mMaterialHeader?.setShowBezierWave(true)
        //设置下拉刷新主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)

    }

    private fun initVideoViewConfig() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        //设置旋转
        orientationUtils = OrientationUtils(this, mVideoView)
        //是否旋转
        mVideoView.isRotateViewAuto = false
        //是否可以滑动调整
        mVideoView.setIsTouchWiget(true)

        //增加封面
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        GlideApp.with(this)
//                .load(itemData.data?.cover?.feed)
//                .centerCrop()
//                .into(imageView)

        GlideUtils.load(this,itemData.data?.cover?.feed,imageView)


        mVideoView.thumbImageView = imageView

        mVideoView.setStandardVideoAllCallBack(object : VideoListener {

            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, *objects)
                //开始播放了才能旋转和全屏
                orientationUtils?.isEnable = true
                isPlay = true
            }

            override fun onAutoComplete(url: String, vararg objects: Any) {
                super.onAutoComplete(url, *objects)
                Logger.d("***** onAutoPlayComplete **** ")
            }

            override fun onPlayError(url: String, vararg objects: Any) {
                super.onPlayError(url, *objects)
                showToast("播放失败")
            }

            override fun onEnterFullscreen(url: String, vararg objects: Any) {
                super.onEnterFullscreen(url, *objects)
                Logger.d("***** onEnterFullscreen **** ")
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, *objects)
                Logger.d("***** onQuitFullscreen **** ")
                //列表返回的样式判断
                orientationUtils?.backToProtVideo()
            }
        })
        //设置返回按键功能
        mVideoView.backButton.setOnClickListener({ onBackPressed() })
        //设置全屏按键功能
        mVideoView.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils?.resolveByClick()
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            mVideoView.startWindowFullscreen(this, true, true)
        }
        //锁屏事件
        mVideoView.setLockClickListener(object : LockClickListener {
            override fun onClick(view: View?, lock: Boolean) {
                //配合下方的onConfigurationChanged
                orientationUtils?.isEnable = !lock
            }

        })

    }

    override fun initData() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        itemData = intent.getSerializableExtra(Constants.BUNDLE_VIDEO_DATA) as HomeBean.Issue.Item
        isTransition = intent.getBooleanExtra(TRANSITION, false)

        saveWatchVideoHistoryInfo(itemData)
    }

    private fun saveWatchVideoHistoryInfo(watchItem: HomeBean.Issue.Item) {

        //保存之前需要先查询是否有该value 的记录 有则删除m


        val historyMap = WatchHistoryUtils.getAll(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context) as Map<*, *>
        for ((key, _) in historyMap) {
            if (watchItem == WatchHistoryUtils.getObject(Constants.FILE_WATCH_HISTORY_NAME,MyApplication.context, key as String)) {
                WatchHistoryUtils.remove(Constants.FILE_WATCH_HISTORY_NAME,MyApplication.context, key)
            }
        }
        WatchHistoryUtils.putObject(Constants.FILE_WATCH_HISTORY_NAME,MyApplication.context, watchItem,"" + mFormat.format(Date()))




    }

    override fun setErrorMsg(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initLayoutId(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return R.layout.activity_video_detail
    }

    fun initTransition(){

        if(isTransition&&Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            postponeEnterTransition()

            ViewCompat.setTransitionName(mVideoView, IMG_TRANSITION)
            addTransitionListener()
            startPostponedEnterTransition()
        }else{
            loadVideoInfo()
        }


    }

    private fun loadVideoInfo() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        mPresenter.loadVideoInfo(itemData)

    }

    @SuppressLint("NewApi")
    private fun addTransitionListener() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        transition=window.sharedElementEnterTransition
        transition?.addListener(object:Transition.TransitionListener{
            override fun onTransitionEnd(p0: Transition?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                Logger.d("=====onTransitionEnd========")


                loadVideoInfo()
                transition?.removeListener(this)

            }

            override fun onTransitionResume(p0: Transition?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionPause(p0: Transition?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionCancel(p0: Transition?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionStart(p0: Transition?) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


            }

        })




    }
}