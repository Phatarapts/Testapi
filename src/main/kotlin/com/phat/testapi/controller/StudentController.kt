package com.phat.testapi.controller

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController {
    @Autowired
    lateinit var repository: StudentRepository

    @PostMapping("/")
    fun addStudent(@RequestBody request: InfoRequest): StudentEntity {
        return repository.save(
            StudentEntity(
                classID = request.classroom,
                studentName = "${request.title} ${request.firstName} ${request.lastName}"
            )
        )
    }

    @GetMapping("/")
    fun showAllStudentInfo(): Iterable<StudentEntity> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun showStudentInfoById(@PathVariable("id") id: Long): StudentEntity {
        return repository.findById(id).get()
    }

    @PatchMapping("/{id}")
    fun updateStudentName(@RequestBody update: UpdateName, @PathVariable("id") id: Long): StudentEntity {
        val studentData: StudentEntity = repository.findById(id).get()
        studentData.studentName = update.name
        return repository.save(studentData)
    }

    @DeleteMapping
    fun deleteAllStudentInfo(): String {
        repository.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteStudentInfoByID(@PathVariable("id") id: Long): String {
        repository.deleteById(id)
        return "Delete $id Done"
    }

}