package com.phat.testapi.controller

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.response.Response
import com.phat.testapi.sevices.StudentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(val service: StudentService) {

    @PostMapping
    fun addStudent(@RequestBody request: InfoRequest): StudentEntity = service.addNew(request)

    @PostMapping("/{stdId}/{classId}")
    fun registerClass(@PathVariable("stdId") stdId: Long, @PathVariable("classId") classId: Long): String =
        service.regisClass(stdId, classId)

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