package com.phat.testapi.controller

import com.phat.testapi.exception.NotFoundDataException
import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.response.StudentResponse
import com.phat.testapi.sevices.StudentService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(val service: StudentService) {

    @PostMapping
    fun addStudent(@RequestBody request: InfoRequest): StudentEntity = service.addNew(request)

    @GetMapping
    fun showAllStudentInfo(): Iterable<StudentEntity> = service.getAll()

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun showOneStudentInfo(@PathVariable("id") id: Long): ResponseEntity<StudentResponse> = try {
        ResponseEntity(service.findStudent(id), HttpStatus.OK)
    } catch (e: NotFoundDataException) {
        ResponseEntity.notFound().build()
    }


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