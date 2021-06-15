package com.phat.testapi.repository

import com.phat.testapi.model.entity.ProfessorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : JpaRepository<ProfessorEntity, Long>