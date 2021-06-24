package com.phat.testapi.repository


import com.phat.testapi.model.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation


@org.springframework.transaction.annotation.Transactional(
    propagation = Propagation.REQUIRES_NEW,
    isolation = Isolation.READ_UNCOMMITTED,
    timeout = -1,
    //rollbackFor = ,
    //noRollbackFor =
)


@Repository
interface StudentRepository : JpaRepository<StudentEntity, Long> {
    fun findAllByStudentNameContainingOrStudentMajorContainingIgnoreCase(
        studentName: String,
        studentMajor: String
    ): MutableList<StudentEntity>

    //@Query("SELECT COUNT(*) FROM Student s WHERE s.studentId=:id is not null")
    //fun countByID(
    //    id: Long
    //)
}