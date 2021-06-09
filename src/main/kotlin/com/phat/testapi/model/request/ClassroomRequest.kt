package com.phat.testapi.model.request

data class ClassroomRequest(
    val classId: Long,
    val className: String,
    val professorId: Long
)