package com.phat.testapi.repository

import com.phat.testapi.model.Classroom
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassroomRepository : CrudRepository<Classroom, Long> {

}