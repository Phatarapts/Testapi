package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@Table(name = "Classroom")
data class ClassroomEntity(
    @Id
    @Column(name = "classId")
    val classId: Long,

    @Column(name = "className")
    var className: String,

    /* @Column(name = "professorId")
     var professorId: Long,*/
    @ManyToOne
    var professor: ProfessorEntity? = null
) : CommonEntity()