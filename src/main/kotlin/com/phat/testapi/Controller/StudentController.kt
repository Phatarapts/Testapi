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

    @RequestMapping("/student/save")
    fun save(): String{
        repository.save(Student(6401, "Asama", 101))
        repository.save(Student(6402, "Bsama", 101))
        repository.save(Student(6403, "Csama", 102))
        repository.save(Student(6404, "Dsama", 102))
        return "Save Student Done"
    }
    @RequestMapping("/student/save/{id}")
    fun save2(@PathVariable("id")id: Long): String {
        repository.save(Student(id, "Asama", 101))

        return "Save Student Done"
    }

    @RequestMapping("/student/findall")
    fun findAll(): Iterable<Student> {
        return repository.findAll()
    }

    @RequestMapping("/student/find/id/{id}")
    fun findById(@PathVariable("id") id: Long): Student {
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