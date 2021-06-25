package com.phat.testapi.model.entity


import javax.persistence.*

@Entity
@SequenceGenerator(name = "student_seq")
//@NamedQueries(
//    NamedQuery(name = "findByIdGreaterThan3",query = "select s from Student s where s.studentId>2")
//)
@Table(name = "Student")
data class StudentEntity(
    @Id
    @GeneratedValue(generator = "student_seq")
    @Column(name = "studentId")
    val studentId: Long? = null,

    @Column(name = "studentName")
    var studentName: String? = null,

    @Column(name = "studentMajor")
    var studentMajor: String? = null,

    @ManyToOne
    var classroom: ClassroomEntity? = null
) : CommonEntity()