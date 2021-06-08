package com.phat.testapi.controller

import com.phat.testapi.model.ClassroomEntity
import com.phat.testapi.repository.ClassroomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClassroomController{
    @Autowired
    lateinit var repository : ClassroomRepository

    @RequestMapping("/classroom/save")
    fun save(): String{
        repository.save(ClassroomEntity(101, "Thai", 1001))
        repository.save(ClassroomEntity(102, "Eng", 1002))
        return "Save Classroom Done"
    }

    @RequestMapping("/classroom/findall")
    fun findAll(): Iterable<ClassroomEntity> {
        return repository.findAll()
    }

    @RequestMapping("/classroom/find/id/{id}")
    fun findById(@PathVariable("id") id: Long): ClassroomEntity {
        return repository.findById(id).get()
    }
}