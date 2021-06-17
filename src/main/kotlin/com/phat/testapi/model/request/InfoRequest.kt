package com.phat.testapi.model.request

data class InfoRequest(
    val title: String,
    val firstName: String,
    val lastName: String,
    val classId: Long,
    //val clsStudent: List<Long>
)