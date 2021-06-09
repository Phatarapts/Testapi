package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Classroom")
data class ClassroomEntity(
    @Id
    @Column(name = "classID")
    val classID: Long,

    @Column(name = "className")
    var className: String,

    @Column(name = "professorID")
    val professorID: Long

)