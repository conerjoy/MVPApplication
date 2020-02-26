package com.coner.mvpapplication.ui.base

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coner.mvpapplication.common.Constant
import com.trello.rxlifecycle2.components.support.RxFragment
import com.vastkingdom.coner.cob.ui.common.activity.base.AnyView
import java.lang.ref.WeakReference

/**
 * @author coner
 * 基类fragment
 */
abstract class BaseFragment : RxFragment(), AnyView {
    protected var isResume: Boolean = false
    // 最后一次点击的时间
    private var lastClickTime: Long = 0
    protected var isViewCreated: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(setLayout(), container, false)
        return rootView
    }

    /**
     * 使用这种在fragment中直接用id引入控件的方法必须在onViewCreated方法中调用，在onCreateView中控件还未初始化报错空指针
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        setInit()
    }

    /**
     * 计算多次点击时间并返回是否符合跳转间隔
     */
    private fun isCanJump(): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > Constant.MANY_TIME_CLICK_TIME) {// 两次点击的时间间隔大于最小限制时间，则触发跳转事件
            lastClickTime = currentTime
            return true
        }
        return false
    }

    protected fun startActivityHandle(intent: Intent) {
        if (isCanJump()) startActivity(intent)
    }

    protected fun startActivityForResultHandle(intent: Intent, requestCode: Int) {
        if (isCanJump()) startActivityForResult(intent, requestCode)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isViewCreated) {
            if (isVisibleToUser) {
                // 相当于onResume()方法
                onShow()
            } else {
                // 相当于onpause()方法
                onHide()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isResume = true
        onShow()
    }

    /**
     * 可见时调用的方法
     */
    open fun onShow() {}

    open fun onHide() {}


    /**
     * 自定义Handler，防止内存泄漏
     */
    abstract class MyHandler<A : BaseFragment>(fragment: A) : Handler() {
        private val mFragment: WeakReference<A> = WeakReference(fragment)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            mFragment.get()?.let { handleMessage(msg, it) }
        }

        abstract fun handleMessage(msg: Message, fragment: A)
    }
}