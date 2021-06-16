package com.phat.testapi.repository


import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.response.studentDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<StudentEntity, Long> {
    fun findAll()

}