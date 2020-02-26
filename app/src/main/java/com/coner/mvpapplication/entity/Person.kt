package com.coner.mvvmapplication.entity

/**
 * @author coner
 */
class Person {
    var name: String = ""
    var age: Int = 0
    var sex: String = ""
    var gift: String = ""

    constructor(name: String, age: Int, sex: String, gift: String) {
        this.name = name
        this.age = age
        this.sex = sex
        this.gift = gift
    }
}