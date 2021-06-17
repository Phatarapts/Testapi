package com.phat.testapi.repository

import com.phat.testapi.model.entity.ClassroomEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassroomRepository : JpaRepository<ClassroomEntity, Long>