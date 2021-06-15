package com.phat.testapi.model.entity

import javax.persistence.*

@Entity
@SequenceGenerator(name = "professor_seq")
@Table(name = "Professor")
data class ProfessorEntity(
    @Id
    @GeneratedValue(generator = "professor_seq")
    @Column(name = "professorId")
    val professorId: Long? = null,

    @Column(name = "professorName")
    var professorName: String,

    @OneToMany(mappedBy = "professor", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    
    var classroom: List<ClassroomEntity>? = null
): CommonEntity()