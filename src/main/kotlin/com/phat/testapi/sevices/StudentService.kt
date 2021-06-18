package com.phat.testapi.sevices

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.response.Response
import com.phat.testapi.repository.StudentRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service


@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val classroomService: ClassroomService
) {

    fun addNew(request: InfoRequest): StudentEntity {
        val studentData = StudentEntity(
            studentName = "${request.title} ${request.firstName} ${request.lastName}",
            studentMajor = request.major
            //classroom = request.classId
        )
        return studentRepository.save(
            studentData
        )
    }

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

    fun searchByString(data: String): MutableList<StudentEntity> {
        val studentInfo = StudentEntity(studentName = data, studentMajor = data)
        val matcher = ExampleMatcher.matchingAny()
            .withIgnorePaths("version")
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase()
        val example = Example.of(studentInfo, matcher)
        return studentRepository.findAll(example)
    }

    fun searchById(id: Long): MutableList<StudentEntity> {
        val studentInfo = StudentEntity(studentId = id)
        val matcher = ExampleMatcher.matchingAll()
            .withIgnorePaths("version")
        val example = Example.of(studentInfo, matcher)
        return studentRepository.findAll(example)
    }

    /*fun regisClass(stdId: Long, classId: Long) {
        val studentData: StudentEntity =
            studentRepository.findById(stdId).orElseThrow { NotFoundStudentException("Not found student $stdId") }
        if (classroomService.existClassroom(classId)) {
            studentData.classId = classId
            studentRepository.save(studentData)
        } else {
            throw NotFoundClassRoomException("Not found classroom $classId")
        }
    }*/

}

class NotFoundClassRoomException(override val message: String?) : RuntimeException()
class NotFoundStudentException(override val message: String?) : RuntimeException()

