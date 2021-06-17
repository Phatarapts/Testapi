package com.phat.testapi.controller

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.response.Response
import com.phat.testapi.sevices.NotFoundClassRoomException
import com.phat.testapi.sevices.NotFoundStudentException
import com.phat.testapi.sevices.StudentService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(val service: StudentService) {
    private val logger = LoggerFactory.getLogger(StudentController::class.java)

    @PostMapping
    fun addStudent(@RequestBody request: InfoRequest) = service.addNew(request)

    /*@PostMapping("/{stdId}/{classId}")
    fun registerClass(
        @PathVariable("stdId") stdId: Long,
        @PathVariable("classId") classId: Long
    ) = try {
        service.regisClass(stdId, classId)
        ResponseEntity(null, HttpStatus.CREATED)
    } catch (ex: Exception) {
        if (ex is NotFoundClassRoomException) {
            logger.error("Error: ${ex.message}", ex)
        }
        if (ex is NotFoundStudentException) {
            logger.error("Error: ${ex.message}", ex)
        }
        ResponseEntity(null, HttpStatus.NOT_FOUND)
    }*/


    @GetMapping
    fun showAllStudentInfo(): Iterable<Response> = service.getAll()

    @GetMapping("/{id}")
    fun showOneStudentInfo(@PathVariable("id") id: Long): StudentEntity = service.getOne(id)

    @GetMapping("/check/{id}")
    fun showOneExist(@PathVariable("id") id: Long): Boolean = service.existStudent(id)

    @PatchMapping("/{id}")
    fun updateStudentByID(@RequestBody update: UpdateName, @PathVariable("id") id: Long): StudentEntity =
        service.updateName(update, id)

    @DeleteMapping
    fun deleteAllStudentInfo(): String {
        service.deleteAll()
        return "Delete Done"
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteOneStudentInfo(@PathVariable("id") id: Long): String {
        service.deleteOne(id)
        return "Delete $id Done"
    }


}