package com.phat.testapi.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class StudentResponse(
    val id: Long,
    val name: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val updateDate: LocalDate?
) : Serializable