package com.coner.mvpapplication.ui.mainactivity

import com.coner.mvvmapplication.entity.Person

/**
 * @author coner
 */
class MainContract {
    interface Presenter {
        fun getFirstPerson()
        fun getSecondPerson()
    }

    interface Model {
        fun getFirstPerson(callBack: (Person) -> Unit)
        fun getSecondPerson(callBack: (Person) -> Unit)
    }

    interface View {
        fun onGetFirstPerson(person: Person)
        fun onGetSecondPerson(person: Person)
    }
}