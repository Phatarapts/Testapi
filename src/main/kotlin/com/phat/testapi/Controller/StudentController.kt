package com.phat.testapi.Controller

import com.phat.testapi.model.Student
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class StudentController{
    @Autowired
    lateinit var repository : StudentRepository

    @GetMapping("/")
    fun hello() = "Hello World"

    @PostMapping("/student/addinfo")
    fun addStudentInfo(): String{
        repository.save(Student( "Asama", 101))
        repository.save(Student( "Bsama", 101))
        repository.save(Student( "Csama", 102))
        repository.save(Student( "Dsama", 102))
        return "Save Student Done"
    }


    @GetMapping("/student/getall")
    fun getAllStudentInfo(): Iterable<Student> {
        return repository.findAll()
    }

    @GetMapping("/student/get/id/{id}")
    fun getStudentInfoById(@PathVariable("id") id: Long): Student {
        return repository.findById(id).get()
    }

    @PatchMapping("/student/patchinfo/{id}")
    fun patchStudentInfo(@PathVariable("id") id: Long): String{
        val studentdata : Optional<Student> = repository.findById(id)

        return "patch Done"
    }


    @DeleteMapping("/student/deleteall")
    fun deleteAllStudentInfo():String{
        repository.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping(value = ["/student/delete/{id}"],method = [RequestMethod.DELETE])
    fun deleteStudentInfoByID(@PathVariable("id") id: Long): String {
        repository.deleteById(id)
        return "Delete "+id+" Done"
    }
}