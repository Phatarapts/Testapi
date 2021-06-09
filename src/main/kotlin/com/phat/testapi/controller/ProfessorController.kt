package com.phat.testapi.controller

import com.phat.testapi.model.entity.ProfessorEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.sevices.ProfessorService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/professor")
class ProfessorController(val service: ProfessorService) {

    @PostMapping("/")
    fun addProfessor(@RequestBody request: InfoRequest): ProfessorEntity = service.addNew(request)

    @GetMapping("/")
    fun showAllProfessorInfo(): Iterable<ProfessorEntity> = service.getAll()

    @GetMapping("/{id}")
    fun showProfessorInfoById(@PathVariable("id") id: Long): ProfessorEntity = service.getOne(id)

    @PatchMapping("/{id}")
    fun updateProfessorName(@RequestBody update: UpdateName, @PathVariable("id") id: Long): ProfessorEntity =
        service.updateName(update, id)

    @DeleteMapping
    fun deleteAllProfessorInfo(): String {
        service.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteProfessorInfoByID(@PathVariable("id") id: Long): String {
        service.deleteOne(id)
        return "Delete $id Done"
    }
}