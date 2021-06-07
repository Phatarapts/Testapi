package com.phat.testapi.Controller

import com.phat.testapi.model.Student
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class StudentController{
    @Autowired
    lateinit var repository : StudentRepository

    @GetMapping("/")
    fun helloMedium() = "Hello World"

    @RequestMapping("/student/post")
    fun post(): String{
        repository.save(Student(6401, "Asama", 101))
        repository.save(Student(6402, "Bsama", 101))
        repository.save(Student(6403, "Csama", 102))
        repository.save(Student(6404, "Dsama", 102))
        return "Save Student Done"
    }
    @RequestMapping("/student/post/{id}")
    fun postByID(@PathVariable("id")id: Long): String {
        repository.save(Student(id, "Asama", 101))

        return "Save Student Done"
    }

    @GetMapping("/student/getall")
    fun getAll(): Iterable<Student> {
        return repository.findAll()
    }

    @GetMapping("/student/get/id/{id}")
    fun getById(@PathVariable("id") id: Long): Student {
        return repository.findById(id).get()
    }



    @RequestMapping("/student/deleteall")
    fun deleteAll():String{
        repository.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping("/student/delete/{id}")
    fun deleteByID(@PathVariable("id") id: Long): String {
        repository.deleteById(id)
        return "Delete "+id+" Done"
    }
}