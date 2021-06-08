package com.phat.testapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Classroom")
open class ClassroomEntity{
    @Id
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "name")
    var name: String? =null

    @Column(name = "professor")
    var professor : Long? =null

    protected constructor()

    constructor(id : Long,name: String,professor : Long){
        this.id = id
        this.name = name
        this.professor = professor
    }
}