package com.coner.mvpapplication.common

class Constant {
    companion object {
        var isDebug = false
        val HTTP_URL_PATH = if (isDebug) "http://www.xxxxx.xxxx/" else "https://www.xxxxx.xxxxx/"
        // 防止多次点击而触发的限制时间
        val MANY_TIME_CLICK_TIME = 300
        val OUTER_EXIT_SCHEME = ExitScheme.HOMEEXIT
        // 双击退出应用时间
        val DOUBLE_CLICK_EXIT_TIME = 2000
        // 服务器正确返回的code
        val SUCCESS_RESPONSE_CODE = 200
        val AGAIN_SEND_TIME = 60
    }

    /**
     * 外围activity退出方式
     * DOUBLECLICKEXIT 双击退出应用
     * HOMEEXIT 返回键执行home事件
     */
    class ExitScheme {
        companion object {
            val DOUBLECLICKEXIT = 0
            val HOMEEXIT = 1
        }
    }
}