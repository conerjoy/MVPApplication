package com.vastkingdom.coner.cob.ui.common.activity.base

/**
 * @author coner
 * 每个activity或者fragment必须实现的方法
 */
interface AnyView {
    /**
     * 返回布局id
     */
    fun setLayout(): Int

    /**
     * 返回初始化内容
     */
    fun setInit()
}