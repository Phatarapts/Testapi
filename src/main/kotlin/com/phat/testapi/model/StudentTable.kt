package com.phat.testapi.model

import javax.persistence.*

@Entity
@Table(name = "Student")
open class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "name")
    var name: String? =null

    @Column(name = "classroom")
    var classroom : Long? =null

    protected constructor()

    constructor(name: String,classroom : Long){

        this.name = name
        this.classroom = classroom
    }
}