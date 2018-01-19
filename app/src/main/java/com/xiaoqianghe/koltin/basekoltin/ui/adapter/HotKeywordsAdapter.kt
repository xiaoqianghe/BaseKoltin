package com.xiaoqianghe.koltin.basekoltin.ui.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayoutManager
import com.xiaoqianghe.koltin.basekoltin.R
import com.xiaoqianghe.koltin.basekoltin.view.recyclerview.ViewHolder
import com.xiaoqianghe.koltin.basekoltin.view.recyclerview.adapter.CommonAdapter
import kotlinx.android.synthetic.main.activity_search.view.*


/**
 *
 * Author：Wq
 * Date：2018/1/19 16:33
 * Description：//todo
 *
 *
 */
class HotKeywordsAdapter(mContext: Context, mList:ArrayList<String>, layoutId:Int)
    :CommonAdapter<String>(mContext,mList,layoutId){



    private var mOnTagItemClick : ((tag: String) -> Unit)? =null


    fun setOnTagItemClickListener(onTagItemClickListener:(tag:String) -> Unit){

        this.mOnTagItemClick = onTagItemClickListener

    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    holder.setText(R.id.tv_title,data)

        val params = holder.getView<TextView>(R.id.tv_title).layoutParams
        if(params is FlexboxLayoutManager.LayoutParams){
            params.flexGrow = 1.0f
        }

        holder.setOnItemClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                mOnTagItemClick?.invoke(data)
            }

        }

        )

    }
}