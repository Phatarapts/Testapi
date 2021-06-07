package com.phat.testapi.Controller

import com.phat.testapi.model.Classroom
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
        repository.save(Classroom(101, "Thai", 1001))
        repository.save(Classroom(102, "Eng", 1002))
        return "Save Classroom Done"
    }

    @RequestMapping("/classroom/findall")
    fun findAll(): Iterable<Classroom> {
        return repository.findAll()
    }

    @RequestMapping("/classroom/find/id/{id}")
    fun findById(@PathVariable("id") id: Long): Classroom {
        return repository.findById(id).get()
    }
}