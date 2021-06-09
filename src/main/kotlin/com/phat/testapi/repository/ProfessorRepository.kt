package com.phat.testapi.repository

import com.phat.testapi.model.entity.ProfessorEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : CrudRepository<ProfessorEntity, Long>