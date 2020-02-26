package com.vastkingdom.coner.cob.ui.common.activity.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ActivityCompat
import androidx.core.util.Pair
import com.coner.mvpapplication.common.Constant
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author coner
 * 基类activity
 */
abstract class BaseActivity : RxAppCompatActivity(), AnyView {
    protected lateinit var mContext: Context
    protected var mToolbar: Toolbar? = null
    // 需要控制焦点游标的edittext
    private var mOnNavigationClick: ((View) -> Unit)? = null
    // 最后一次点击的时间
    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createInit()
    }

    private fun createInit() {
        setViewBefore()
        setContentView(setLayout())
        hanldeToolbar()
        mContext = this
        // 这里的导航栏颜色默认设置为白色
        window.navigationBarColor = Color.WHITE
        // 初始化一定是最后执行的
        setInit()
    }


    /*
    =====================================================================================================================================
     */
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

    /**
     * 启用共享元素的转场动画
     */
    protected fun startActivityTransitionHandle(
        intent: Intent,
        requestCode: Int? = null,
        elements: ArrayList<Pair<View, String>>
    ) {
        val elements = elements.toArray(emptyArray<Pair<View, String>>())
        // 拥有共享元素并且版本至少为21
        if (Build.VERSION.SDK_INT >= 21 && elements.isNotEmpty()) {
            val option =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, *elements).toBundle()
            if (requestCode == null) {
                ActivityCompat.startActivity(this, intent, option)
            } else {
                ActivityCompat.startActivityForResult(this, intent, requestCode, option)
            }
        } else {
            if (requestCode == null) {
                startActivity(intent)
            } else {
                startActivityForResult(intent, requestCode)
            }
        }
    }

    /**
     * 处理toolbar的方法
     */
    private fun hanldeToolbar() {
        mToolbar = setToolBar {
            onBackPressed()
        }
        mToolbar?.setNavigationOnClickListener {
            mOnNavigationClick?.let { click -> click(it) }
        } ?: return
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        setInit()
    }

    /**
     * 返回toolbar实例，用于统一控制返回按钮
     * 基类默认返回null，子类重写返回自定义的toolbar交给基类处理
     * @param onNavigationClick toolbar的返回按钮的点击事件
     */
    open fun setToolBar(onNavigationClick: (View) -> Unit): Toolbar? {
        mOnNavigationClick = onNavigationClick
        return null
    }

    /**
     * setContentView之前需要设置的内容
     */
    open fun setViewBefore() {}

    /**
     * 侧滑返回事件发生
     */
    open fun onSlideHappened() {}

    /**
     * 自定义Handler，防止内存泄漏
     */
//    abstract class MyHandler<A : BaseActivity>(activity: A) : Handler() {
//        private val mActivity: WeakReference<A> = WeakReference(activity)
//
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            mActivity.get()?.let { handleMessage(msg, it) }
//        }
//
//        abstract fun handleMessage(msg: Message, activity: A)
//    }
}