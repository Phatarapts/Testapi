package com.phat.testapi.controller

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.response.Student
import com.phat.testapi.sevices.StudentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(val service: StudentService) {

    @PostMapping
    fun addStudent(@RequestBody request: InfoRequest): StudentEntity = service.addNew(request)

    @GetMapping
    fun showAllStudentInfo(): Iterable<Student> = service.getAll().map {
        Student(
            id = it.studentId.toString(),
            name = it.studentName,
        )
    }

    @GetMapping("/{id}")
    fun showOneStudentInfo(@PathVariable("id") id: Long): StudentEntity = service.getOne(id)

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