package com.phat.testapi.sevices

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.response.Response
import com.phat.testapi.repository.StudentRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@EnableAsync
@EnableScheduling
@EnableRetry
@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val classroomService: ClassroomService
) {
    val logger: Logger = LoggerFactory.getLogger("com.example.app")
    val dateTime: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
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

    //@Async
    //@Scheduled(fixedDelay = 200)
    //@Scheduled(fixedRate = 1)
    //@Scheduled(cron = "1/1 * * * * *")
    //@Scheduled(initialDelay=10000, fixedRate=5000)
    //@Scheduled(cron = "\${my.cron.value}")

    fun getAll(): Iterable<Response> = studentRepository.findAll().map {
        logger.info("Thread name: {}", Thread.currentThread().name)
        logger.info("Time - {}", dateTime.format(LocalDateTime.now()))
        Response(
            id = it.studentId,
            name = it.studentName
        )

    }

    @Retryable(maxAttempts = 3, backoff = Backoff(delay = 500))//ใส่value
    fun getOne(id: Long): StudentEntity {
        logger.info("Time - {}", dateTime.format(LocalDateTime.now()))
        return studentRepository.findById(id).get()
    }

    @Recover
    fun showError(): Nothing {
        logger.info("Time - {}", dateTime.format(LocalDateTime.now()))
        throw NotFoundIdException()
    }


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

    fun searchByStringUseNaming(data: String): MutableList<StudentEntity> {
        return studentRepository.findAllByStudentNameContainingOrStudentMajorContainingIgnoreCase(data, data)
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
class NotFoundIdException : RuntimeException()

