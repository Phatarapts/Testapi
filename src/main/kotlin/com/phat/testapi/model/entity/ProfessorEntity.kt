package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Professor")
data class ProfessorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "professorID")
    val professorID: Long? = null,

    @Column(name = "professorName")
    var professorName: String,

    @Column(name = "classID")
    val classID: Long


)