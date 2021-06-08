package com.phat.testapi.repository

import com.phat.testapi.model.entity.StudentEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<StudentEntity, Long>