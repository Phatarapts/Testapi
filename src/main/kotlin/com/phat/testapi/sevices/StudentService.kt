package com.phat.testapi.sevices

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.response.Response
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class StudentService {
    @Autowired
    lateinit var repository: StudentRepository
    lateinit var service: ClassroomService

    fun addNew(request: InfoRequest): StudentEntity = repository.save(
        StudentEntity(
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

    fun getAll(): Iterable<Response> = repository.findAll().map {
        Response(
            id = it.studentId,
            name = it.studentName
        )
    }

    fun getOne(id: Long): StudentEntity = repository.findById(id).get()

    fun deleteAll() = repository.deleteAll()

    fun deleteOne(id: Long) = repository.deleteById(id)

    fun existStudent(id: Long): Boolean = repository.existsById(id)

    fun regisClass(stdId: Long, classId: Long): String {
        return if (existStudent(stdId)) {
            if (service.existClassroom(classId)) {
                repository.save(StudentEntity(classId = classId))
                "regis done"
            } else "classroom doesn't exist"
        } else
            "student doesn't exist"
    }
}