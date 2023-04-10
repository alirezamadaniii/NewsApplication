package com.example.global.modules.shared

object GenderList {
    fun list(): ArrayList<Gender> {
        val listGender: ArrayList<Gender> = arrayListOf()
        val genderMan = Gender(1, "مرد")
        val genderWoman = Gender(2, "زن")
        listGender.add(genderMan)
        listGender.add(genderWoman)
        return listGender
    }
}