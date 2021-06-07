package com.phat.testapi.repository

import com.phat.testapi.model.Professor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessorRepository : CrudRepository<Professor, Long> {

}