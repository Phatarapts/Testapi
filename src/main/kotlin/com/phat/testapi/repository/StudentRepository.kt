package com.phat.testapi.repository


import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.response.studentDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation


@org.springframework.transaction.annotation.Transactional(
    propagation = Propagation.REQUIRES_NEW,
    isolation = Isolation.READ_UNCOMMITTED,
    timeout = -1
)


@Repository
interface StudentRepository : JpaRepository<StudentEntity, Long>
