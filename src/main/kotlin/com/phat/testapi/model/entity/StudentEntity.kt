package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Student")
data class StudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studentID")
    val studentID: Long? = null,

    @Column(name = "name")
    var studentName: String,

    @Column(name = "classID")
    val classID: Long


)