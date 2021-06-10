package com.phat.testapi.sevices

import com.phat.testapi.exception.NotFoundDataException
import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.response.StudentResponse
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*


@Service
class StudentService {
    @Autowired
    lateinit var repository: StudentRepository

    fun addNew(request: InfoRequest): StudentEntity = repository.save(
        StudentEntity(
            classId = request.classroom,
            studentName = "${request.title} ${request.firstName} ${request.lastName}",
            postDate = LocalDate.now()
        )
    )

    fun updateName(update: UpdateName, id: Long): StudentEntity {
        val studentData: StudentEntity = repository.findById(id).get()
        studentData.studentName = update.name
        studentData.updateDate = LocalDate.now()
        return repository.save(studentData)
    }

    fun getAll(): Iterable<StudentEntity> = repository.findAll()

    fun getOne(id: Long): StudentEntity = repository.findById(id).orElseThrow {
        throw NotFoundDataException("Not found student id: $id in database")
    }

    fun findStudent(studentId: Long): StudentResponse {
        val existStudent = repository.findById(studentId).orElseThrow {
            throw NotFoundDataException("Not found student id: $studentId at table Student")
        }
        return StudentResponse(
            id = existStudent.studentId!!,
            name = existStudent.studentName,
            updateDate = existStudent.updateDate

        )
    }

    fun deleteAll() = repository.deleteAll()

    fun deleteOne(id: Long) = repository.deleteById(id)
}