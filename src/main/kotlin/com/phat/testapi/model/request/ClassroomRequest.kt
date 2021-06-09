package com.phat.testapi.model.request

data class ClassroomRequest(
    val classID: Long,
    val className: String,
    val professorID: Long
)