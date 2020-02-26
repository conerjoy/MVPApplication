package com.vastkingdom.coner.cob.ui.common.activity.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coner.mvpapplication.ui.base.BaseFragment

/**
 * @author coner
 * MVP模式的基类fragment
 */
abstract class MVPFragment<P : MVPPresenter<*, *>> : BaseFragment, MVPView<P> {
    protected lateinit var mPresenter: P

    constructor() : super()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter = createPresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.clear()
    }
}