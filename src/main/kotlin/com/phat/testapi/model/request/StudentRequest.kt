package com.phat.testapi.model.request

import javax.persistence.*

@Entity
@Table(name = "Request")
open class StudentRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Long,


    @Column(name = "title")
    val title: String,

    @Column(name = "fristname")
    val fristname: String,

    @Column(name = "lastname")
    val lastname: String,

    @Column(name = "name")
    var name: String="$title $fristname $lastname",

    @Column(name = "classroom")
    val classroom : Long


)