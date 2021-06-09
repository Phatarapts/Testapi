package com.phat.testapi.sevices

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService {
    @Autowired
    lateinit var repository: StudentRepository

    fun addNew(request: InfoRequest): StudentEntity = repository.save(
        StudentEntity(
            classId = request.classroom,
            studentName = "${request.title} ${request.firstName} ${request.lastName}"
        )
    )

    fun updateName(update: UpdateName, id: Long): StudentEntity {
        val studentData: StudentEntity = repository.findById(id).get()
        studentData.studentName = update.name
        return repository.save(studentData)
    }

    fun getAll(): Iterable<StudentEntity> = repository.findAll()

    fun getOne(id: Long): StudentEntity = repository.findById(id).get()

    fun deleteAll() = repository.deleteAll()

    fun deleteOne(id: Long) = repository.deleteById(id)
}