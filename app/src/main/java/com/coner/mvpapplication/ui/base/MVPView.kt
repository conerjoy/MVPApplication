package com.vastkingdom.coner.cob.ui.common.activity.base

/**
 * @author coner
 */
interface MVPView<P> {
    fun createPresenter(): P
}