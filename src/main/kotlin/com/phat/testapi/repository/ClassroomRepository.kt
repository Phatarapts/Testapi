package com.phat.testapi.repository

import com.phat.testapi.model.ClassroomEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassroomRepository : CrudRepository<ClassroomEntity, Long>