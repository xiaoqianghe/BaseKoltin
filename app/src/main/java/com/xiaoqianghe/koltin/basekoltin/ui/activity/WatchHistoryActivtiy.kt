package com.xiaoqianghe.koltin.basekoltin.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.xiaoqianghe.basekoltin.utils.StatusBarUtil
import com.xiaoqianghe.basekoltin.utils.WatchHistoryUtils
import com.xiaoqianghe.koltin.basekoltin.Constants
import com.xiaoqianghe.koltin.basekoltin.MyApplication
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.base.BaseActivity
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.ui.adapter.WitchHistoryAdapter

import kotlinx.android.synthetic.main.layout_watch_history.*
import java.util.*


/**
 *
 * Author：Wq
 * Date：2018/1/18 15:53
 * Description：//todo
 *
 *
 */
class WatchHistoryActivtiy :BaseActivity() {
    override fun start() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var itemListData =ArrayList<HomeBean.Issue.Item>()

    companion object {
        private val HISTORY_MAX=20

    }
    override fun initStart() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun initListener() {
//  //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun initView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    toolbar.setNavigationOnClickListener {
        finish()
    }

        val mAadapter=WitchHistoryAdapter(this,itemListData,R.layout.item_video_small_card)


        mRecyclerView.layoutManager=LinearLayoutManager(this)

        mRecyclerView.adapter=mAadapter

        if(itemListData.size>1) {
            multipleStatusView.showContent()
        }else{
            multipleStatusView.showEmpty()
        }

        //
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this,toolbar)
        StatusBarUtil.setPaddingSmart(this,mRecyclerView)






    }

    override fun initData() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        multipleStatusView.showLoading()

        itemListData=queryWitchHistory()

    }

    private fun queryWitchHistory(): ArrayList<HomeBean.Issue.Item> {

        val watchList=ArrayList<HomeBean.Issue.Item>()
        val hisAll = WatchHistoryUtils.getAll(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context) as Map<*, *>
        //将key排序升序
        val keys = hisAll.keys.toTypedArray()
        Arrays.sort(keys)
        val keyLength = keys.size
        //这里计算 如果历史记录条数是大于 可以显示的最大条数，则用最大条数做循环条件，防止历史记录条数-最大条数为负值，数组越界
        val hisLength = if (keyLength > HISTORY_MAX) HISTORY_MAX else keyLength
        // 反序列化和遍历 添加观看的历史记录
        (1..hisLength).mapTo(watchList) {
            WatchHistoryUtils.getObject(Constants.FILE_WATCH_HISTORY_NAME,MyApplication.context,
                    keys[keyLength - it] as String) as HomeBean.Issue.Item
        }

        return watchList
    }

    override fun initLayoutId(): Int {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return R.layout.layout_watch_history

    }
}