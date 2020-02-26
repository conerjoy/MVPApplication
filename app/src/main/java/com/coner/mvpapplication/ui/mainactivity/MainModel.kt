package com.coner.mvpapplication.ui.mainactivity

import com.coner.mvvmapplication.entity.Person
import java.util.*

/**
 * @author coner
 */
class MainModel : MainContract.Model {
    var timer: Timer = Timer()

    override fun getFirstPerson(callBack: (Person) -> Unit) {
        // 模拟网络请求，使用okhttp + rxAndroid或者其他框架可以很好的处理主线程与异步线程，所以这里不用考虑在子线程更新控件的问题
        timer.schedule(object : TimerTask() {
            override fun run() {
                callBack.invoke(Person("周杰伦", 40, "男", "唱歌，写歌"))
            }
        }, 500)
    }

    override fun getSecondPerson(callBack: (Person) -> Unit) {
        // 模拟网络请求，使用okhttp + rxAndroid或者其他框架可以很好的处理主线程与异步线程，所以这里不用考虑在子线程更新控件的问题
        timer.schedule(object : TimerTask() {
            override fun run() {
                callBack.invoke(Person("佟丽娅", 37, "女", "表演"))
            }
        }, 800)
    }
}