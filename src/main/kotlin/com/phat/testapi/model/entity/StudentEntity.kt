package com.phat.testapi.model.entity


import javax.persistence.*

@Entity
@SequenceGenerator(name = "student_seq")
@Table(name = "Student")
data class StudentEntity(
    @Id
    @GeneratedValue(generator = "student_seq")
    @Column(name = "studentId")
    val studentId: Long? = null,

    @Column(name = "studentName")
    var studentName: String,

    @Column(name = "classId")
    var classId: Long? = null,

): CommonEntity()