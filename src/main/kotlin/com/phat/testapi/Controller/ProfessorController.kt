package com.phat.testapi.Controller

import com.phat.testapi.model.Professor
import com.phat.testapi.repository.ProfessorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfessorController{
    @Autowired
    lateinit var repository : ProfessorRepository

    @RequestMapping("/professor/save")
    fun save(): String{
        repository.save(Professor(1001, "ATea", 101))
        repository.save(Professor(1002, "BTea", 102))
        return "Save Professor Done"
    }

    @RequestMapping("/professor/findall")
    fun findAll(): Iterable<Professor> {
        return repository.findAll()
    }

    @RequestMapping("/professor/find/id/{id}")
    fun findById(@PathVariable("id") id: Long): Professor {
        return repository.findById(id).get()
    }
}