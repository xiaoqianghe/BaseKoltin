package com.xiaoqianghe.koltin.basekoltin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

import android.support.design.widget.TabLayout
import android.util.Log
import com.hazz.kotlinmvp.utils.DisplayManager
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates


/**
 *
 * Author：Wq
 * Date：2017/12/21 16:14
 * Description：//todo
 *
 *
 */
class MyApplication : Application() {

    private var refWatcher : RefWatcher? =null

    companion object {
        private val TAG="MyApplication"

        var context : Context by Delegates.notNull()

        private set

        fun  getRefWatcher(context: Context) : RefWatcher?{

            val myApplication =context.applicationContext as MyApplication

            return myApplication.refWatcher

        }
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        refWatcher=setupLeakCanary()
        initConfig()

        DisplayManager.init(this)

        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)


    }

    private fun initConfig() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupLeakCanary(): RefWatcher? {

        return if(LeakCanary.isInAnalyzerProcess(this)){
            RefWatcher.DISABLED
        }else LeakCanary.install(this)
    }



    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }


}