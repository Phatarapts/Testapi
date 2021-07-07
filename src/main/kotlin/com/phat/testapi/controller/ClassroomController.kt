package com.phat.testapi.controller

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.request.ClassroomRequest
import com.phat.testapi.sevices.ClassroomService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/classroom")
class ClassroomController(val service: ClassroomService) {

    @PostMapping("/")
    fun addClassroom(@RequestBody request: ClassroomRequest): String {
        service.addClass(request)
        return "{\"message\":\"ok\"}"
    }

    @GetMapping("/")
    fun showAllClassroomInfo(): Iterable<ClassroomEntity> = service.getAll()

    @GetMapping("/{id}")
    fun showClassroomInfoById(@PathVariable("id") id: Long): ClassroomEntity = service.getOneClass(id)

    @PatchMapping("/{id}")
    fun updateClassName(@RequestBody update: ClassroomRequest, @PathVariable("id") id: Long): ClassroomEntity =
        service.updateClassName(update, id)

    @DeleteMapping
    fun deleteAllClassroomInfo(): String {
        service.deleteAll()
        return "DeleteDone"
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteClassroomInfoByID(@PathVariable("id") id: Long): String {
        service.deleteOne(id)
        return "Delete $id Done"
    }

}