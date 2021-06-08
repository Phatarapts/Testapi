package com.phat.testapi.controller

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/student")
class StudentController{
    @Autowired
    lateinit var repository : StudentRepository


    @PostMapping("/addinfo")
    fun addStudentInfo(): String{
        repository.save(StudentEntity( id = null,"Asama", 101))
        repository.save(StudentEntity( id =null,"Bsama", 101))

        return "Save Student Done"
    }


    @GetMapping("/getall")
    fun getAllStudentInfo(): Iterable<StudentEntity> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun getStudentInfoById(@PathVariable("id") id: Long): StudentEntity {
        return repository.findById(id).get()
    }

    @PatchMapping("/patchinfo/{id}")
    fun patchStudentInfo(@PathVariable("id") id: Long): String{
        var studentdata : Optional<StudentEntity> = repository.findById(id)

        return "patch Done"
    }


    @DeleteMapping
    fun deleteAllStudentInfo():String{
        repository.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping(value = ["/{id}"],method = [RequestMethod.DELETE])
    fun deleteStudentInfoByID(@PathVariable("id") id: Long): String {
        repository.deleteById(id)
        return "Delete "+id+" Done"
    }
}