package com.vastkingdom.coner.cob.ui.common.activity.base

import android.os.Bundle

/**
 * @author coner
 * MVP模式的基类activity
 */
abstract class MVPActivity<P : MVPPresenter<*, *>> : BaseActivity(), MVPView<P> {
    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = createPresenter()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mPresenter.clear()
        super.onDestroy()
    }
}