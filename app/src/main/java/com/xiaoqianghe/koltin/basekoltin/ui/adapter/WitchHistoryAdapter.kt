package com.xiaoqianghe.koltin.basekoltin.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.xiaoqianghe.koltin.basekoltin.Constants
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.durationFormat
import com.xiaoqianghe.koltin.basekoltin.mvp.model.bean.HomeBean
import com.xiaoqianghe.koltin.basekoltin.ui.activity.VideoDetailActivity
import com.xiaoqianghe.koltin.basekoltin.utils.GlideUtils
import com.xiaoqianghe.koltin.basekoltin.view.recyclerview.ViewHolder
import com.xiaoqianghe.koltin.basekoltin.view.recyclerview.adapter.CommonAdapter


/**
 *
 * Author：Wq
 * Date：2018/1/18 16:07
 * Description：//todo
 *
 *
 */
class WitchHistoryAdapter(context: Context,dataList:ArrayList<HomeBean.Issue.Item>,layoutId:Int):CommonAdapter<HomeBean.Issue.Item>(context,dataList,layoutId) {

    //绑定数据

    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        with(holder) {
            setText(R.id.tv_title, data.data?.title!!)
            setText(R.id.tv_tag, "#${data.data.category} / ${durationFormat(data.data.duration)}")
            setImagePath(R.id.iv_video_small_card, object : ViewHolder.HolderImageLoader(data.data.cover.detail) {
                override fun loadImage(iv: ImageView, path: String) {
//                    GlideApp.with(mContext)
//                            .load(path)
//                            .placeholder(R.drawable.placeholder_banner)
//                            .transition(DrawableTransitionOptions().crossFade())
//                            .into(iv)

                    GlideUtils.load(mContext,path,iv)
                }
            })
        }
        holder.getView<TextView>(R.id.tv_title).setTextColor(mContext.resources.getColor(R.color.color_black))
        holder.setOnItemClickListener(listener = View.OnClickListener {
            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_video_small_card), data)
        })


    }

    private fun goToVideoPlayer(activity: Activity, view: View, data: HomeBean.Issue.Item) {
        val intent= Intent(activity,VideoDetailActivity::class.java)
        intent.putExtra(Constants.BUNDLE_VIDEO_DATA,data)
        intent.putExtra(VideoDetailActivity.Companion.TRANSITION,true)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }



    }


}