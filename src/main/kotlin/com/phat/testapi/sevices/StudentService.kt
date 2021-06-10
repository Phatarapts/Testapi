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
class StudentService(
    private val studentRepository: StudentRepository,
    private val classroomService: ClassroomService
) {

    fun addNew(request: InfoRequest): StudentEntity = studentRepository.save(
        StudentEntity(
            studentName = "${request.title} ${request.firstName} ${request.lastName}",
            postDate = LocalDate.now()
        )
    )

    fun updateName(update: UpdateName, id: Long): StudentEntity {
        val studentData: StudentEntity = studentRepository.findById(id).get()
        studentData.studentName = update.name
        studentData.updateDate = LocalDate.now()
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

    fun regisClass(stdId: Long, classId: Long): String {
        return if (existStudent(stdId)) {
            if (classroomService.existClassroom(classId)) {
                val studentData: StudentEntity = studentRepository.findById(stdId).get()
                val update = StudentEntity(
                    studentId = studentData.studentId,
                    classId = classId,
                    studentName = studentData.studentName,
                    updateDate = LocalDate.now()
                )
                studentRepository.save(update)
                "Done"
            } else {
                throw RuntimeException()
            }
        } else {
            throw RuntimeException()
        }
    }
}