package com.phat.testapi.controller

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.request.ClassroomRequest
import com.phat.testapi.repository.ClassroomRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/classroom")
class ClassroomController {
    @Autowired
    lateinit var repository: ClassroomRepository

    @PostMapping("/")
    fun addClassroom(@RequestBody request: ClassroomRequest): ClassroomEntity {
        return repository.save(
            ClassroomEntity(
                classID = request.classID,
                className = request.className,
                professorID = request.professorID
            )
        )
    }

    @GetMapping("/")
    fun showAllClassroomInfo(): Iterable<ClassroomEntity> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun showClassroomInfoById(@PathVariable("id") id: Long): ClassroomEntity {
        return repository.findById(id).get()
    }

    @PatchMapping("/{id}")
    fun updateClassName(@RequestBody update: ClassroomRequest, @PathVariable("id") id: Long): ClassroomEntity {
        val classData: ClassroomEntity = repository.findById(id).get()
        classData.className = update.className
        return repository.save(classData)
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