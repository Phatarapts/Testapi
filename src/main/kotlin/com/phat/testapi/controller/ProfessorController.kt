package com.phat.testapi.controller

import com.phat.testapi.model.entity.ProfessorEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.repository.ProfessorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/professor")
class ProfessorController {
    @Autowired
    lateinit var repository: ProfessorRepository

    @PostMapping("/")
    fun addProfessor(@RequestBody request: InfoRequest): ProfessorEntity {
        return repository.save(
            ProfessorEntity(
                classID = request.classroom,
                professorName = "${request.title} ${request.firstName} ${request.lastName}"
            )
        )
    }

    @GetMapping("/")
    fun showAllProfessorInfo(): Iterable<ProfessorEntity> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun showProfessorInfoById(@PathVariable("id") id: Long): ProfessorEntity {
        return repository.findById(id).get()
    }

    @PatchMapping("/{id}")
    fun updateProfessorName(@RequestBody update: UpdateName, @PathVariable("id") id: Long): ProfessorEntity {
        val professorData: ProfessorEntity = repository.findById(id).get()
        professorData.professorName = update.name
        return repository.save(professorData)
    }

    @DeleteMapping
    fun deleteAllProfessorInfo(): String {
        repository.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteProfessorInfoByID(@PathVariable("id") id: Long): String {
        repository.deleteById(id)
        return "Delete $id Done"
    }
}