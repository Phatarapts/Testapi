package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Student")
data class StudentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String,

    @Column(name = "classroom")
    val classroom : Long


)