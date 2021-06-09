package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Professor")
data class ProfessorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "professorId")
    val professorId: Long? = null,

    @Column(name = "professorName")
    var professorName: String,

    @Column(name = "classId")
    val classId: Long


)