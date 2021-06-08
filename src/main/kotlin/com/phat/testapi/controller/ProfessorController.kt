package com.phat.testapi.controller

import com.phat.testapi.model.ProfessorEntity
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
        repository.save(ProfessorEntity(1001, "ATea", 101))
        repository.save(ProfessorEntity(1002, "BTea", 102))
        return "Save Professor Done"
    }

    @RequestMapping("/professor/findall")
    fun findAll(): Iterable<ProfessorEntity> {
        return repository.findAll()
    }

    @RequestMapping("/professor/find/id/{id}")
    fun findById(@PathVariable("id") id: Long): ProfessorEntity {
        return repository.findById(id).get()
    }
}