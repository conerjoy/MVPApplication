package com.coner.mvpapplication.ui.mainactivity

import com.coner.mvpapplication.R
import com.coner.mvvmapplication.entity.Person
import com.vastkingdom.coner.cob.ui.common.activity.base.MVPActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author coner
 */
class MainActivity : MVPActivity<MainPresenter>(), MainContract.View {
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun setInit() {
        nameText.text = "coner"
        ageText.text = "${22}"
        sexText.text = "男"
        giftText.text = "写代码"
        firstButton.setOnClickListener {
            mPresenter.getFirstPerson()
        }
        secondButton.setOnClickListener {
            mPresenter.getSecondPerson()
        }
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun onGetFirstPerson(person: Person) {
        runOnUiThread {
            nameText.text = person.name
            ageText.text = "${person.age}"
            sexText.text = person.sex
            giftText.text = person.gift
        }
    }

    override fun onGetSecondPerson(person: Person) {
        runOnUiThread {
            nameText.text = person.name
            ageText.text = "${person.age}"
            sexText.text = person.sex
            giftText.text = person.gift
        }
    }
}
