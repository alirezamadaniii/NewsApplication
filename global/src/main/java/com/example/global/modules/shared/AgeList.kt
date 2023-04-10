package com.example.global.modules.shared

object AgeList {
    fun list(): ArrayList<AgeFilter> {
        val listGender: ArrayList<AgeFilter> = arrayListOf()
        val ageJunior = AgeFilter(10, 0,10)
        val ageMid = AgeFilter(20, 10,18)
        val ageSenior = AgeFilter(50, 18,50)
        listGender.add(ageJunior)
        listGender.add(ageMid)
        listGender.add(ageSenior)
        return listGender
    }
}