package com.phat.testapi.sevices

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.response.Response
import com.phat.testapi.repository.StudentRepository
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val classroomService: ClassroomService
) {

    fun addNew(request: InfoRequest): StudentEntity = studentRepository.save(
        StudentEntity(
            studentName = "${request.title} ${request.firstName} ${request.lastName}",
        )
    )

    fun updateName(update: UpdateName, id: Long): StudentEntity {
        val studentData: StudentEntity = studentRepository.findById(id).get()
        studentData.studentName = update.name
        return studentRepository.save(studentData)
    }

    fun getAll(): Iterable<Response> = studentRepository.findAll().map {
        Response(
            id = it.studentId,
            name = it.studentName
        )
    }

    fun getOne(id: Long): StudentEntity = studentRepository.findById(id).get()

    fun deleteAll() = studentRepository.deleteAll()

    fun deleteOne(id: Long) = studentRepository.deleteById(id)

    fun existStudent(id: Long): Boolean = studentRepository.existsById(id)

    fun regisClass(stdId: Long, classId: Long) {
        val studentData: StudentEntity =
            studentRepository.findById(stdId).orElseThrow { NotFoundStudentException("Not found student $stdId") }
        if (classroomService.existClassroom(classId)) {
            studentData.classId = classId
            studentRepository.save(studentData)
        } else {
            throw NotFoundClassRoomException("Not found classroom $classId")
        }
    }

}

class NotFoundClassRoomException(override val message: String?) : RuntimeException()
class NotFoundStudentException(override val message: String?) : RuntimeException()

