package com.vastkingdom.coner.cob.ui.common.activity.base

abstract class MVPPresenter<V : MVPView<*>, M> {
    protected var mView: V?
    protected var mModel: M

    constructor(view: V) {
        mView = view
        mModel = createModel()
    }

    fun clear() {
        mView = null
        System.gc()
    }

    abstract fun createModel(): M
}