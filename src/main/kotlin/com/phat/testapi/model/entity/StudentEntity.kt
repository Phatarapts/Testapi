package com.phat.testapi.model.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
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
    val classId: Long,

    @Column(name = "postDate")
    @JsonFormat(pattern = "dd::MM::yyyy")
    var postDate : LocalDate? = null,

    @Column(name = "updateDate")
    @JsonFormat(pattern = "dd::MM::yyyy")
    var updateDate : LocalDate? = null
)