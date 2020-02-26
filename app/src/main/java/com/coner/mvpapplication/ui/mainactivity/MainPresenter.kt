package com.coner.mvpapplication.ui.mainactivity

import com.vastkingdom.coner.cob.ui.common.activity.base.MVPPresenter

/**
 * @author coner
 */
class MainPresenter(view: MainActivity) : MVPPresenter<MainActivity, MainModel>(view),
    MainContract.Presenter {
    override fun createModel(): MainModel {
        return MainModel()
    }

    override fun getFirstPerson() {
        mModel.getFirstPerson {
            mView?.onGetFirstPerson(it)
        }
    }

    override fun getSecondPerson() {
        mModel.getSecondPerson {
            mView?.onGetSecondPerson(it)
        }
    }
}