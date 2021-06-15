package com.phat.testapi.repository

import com.phat.testapi.model.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<StudentEntity, Long>