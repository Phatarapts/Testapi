package com.phat.testapi.model.request

import javax.persistence.*

@Entity
@Table(name = "PatchName")
data class PatchName(
    @Id
    @Column(name = "id")
    val id: Long,

    @Column(name = "name")
    val name: String,

)