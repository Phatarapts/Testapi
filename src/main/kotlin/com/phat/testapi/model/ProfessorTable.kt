package com.phat.testapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Professor")
open class Professor{
    @Id
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "name")
    var name: String? =null

    @Column(name = "classroom")
    var classroom : Long? =null

    protected constructor()

    constructor(id : Long,name: String,classroom : Long){
        this.id = id
        this.name = name
        this.classroom = classroom
    }
}