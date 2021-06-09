package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Student")
data class StudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studentId")
    val studentId: Long? = null,

    @Column(name = "studentName")
    var studentName: String,

    @Column(name = "classId")
    val classId: Long


)