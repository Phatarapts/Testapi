package com.phat.testapi.model.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
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
    val classId: Long? = null,

    @Column(name = "postDate")
    @JsonFormat(pattern = "dd::MM::yyyy")
    var postDate: LocalDate? = null,

    @Column(name = "updateDate")
    @JsonFormat(pattern = "dd::MM::yyyy")
    var updateDate: LocalDate? = null
)