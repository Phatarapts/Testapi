package com.phat.testapi.controller

import com.phat.testapi.model.entity.StudentEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.model.response.Response
import com.phat.testapi.configuration.AppConfig
import com.phat.testapi.sevices.StudentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.retry.annotation.EnableRetry
import org.springframework.web.bind.annotation.*

@EnableRetry
@RestController
@RequestMapping("/student")
class StudentController(val service: StudentService) {
    private val logger = LoggerFactory.getLogger(StudentController::class.java)
    @Value("\${app.info.name}")
    lateinit var name: String
    @Value("\${app.info.email}")
    lateinit var email: String

    @PostMapping
    fun addStudent(@RequestBody request: InfoRequest) = service.addNew(request)

    @GetMapping("/search_info/{info}")
    fun search(@PathVariable("info") info: String) = service.searchByStringUseNaming(info)

    @GetMapping("/search/{id}")
    fun search(@PathVariable("id") id: Long) = service.searchById(id)

    @GetMapping("/plus/{num}")//unresolved ref
    fun plus(@PathVariable("num")num: Long): Number {
        //return num.plus(AppConfig.plus.toInt())
        return 1
    }
    //@GetMapping("/plus")
    //fun plus1(num: Long): Number{
    //    return num.plus(AppConfig.plus.toInt())
    //}

    /*@GetMapping("/search_test_info/{info}")
    fun search2(@PathVariable("info") info: String) = service.searchById2(info)*/

    /*@PostMapping("/{stdId}/{classId}")
    fun registerClass(
        @PathVariable("stdId") stdId: Long,
        @PathVariable("classId") classId: Long
    ) = try {
        service.regisClass(stdId, classId)
        ResponseEntity(null, HttpStatus.CREATED)
    } catch (ex: Exception) {
        if (ex is NotFoundClassRoomException) {
            logger.error("Error: ${ex.message}", ex)
        }
        if (ex is NotFoundStudentException) {
            logger.error("Error: ${ex.message}", ex)
        }
        ResponseEntity(null, HttpStatus.NOT_FOUND)
    }*/


    @GetMapping
    fun showAllStudentInfo(): Iterable<Response> = service.getAll()

    @GetMapping("/{id}")

    fun showOneStudentInfo(@PathVariable("id") id: Long): StudentEntity = service.getOne(id)


    @GetMapping("/check/{id}")
    fun showOneExist(@PathVariable("id") id: Long): Boolean = service.existStudent(id)

    @PatchMapping("/{id}")
    fun updateStudentByID(@RequestBody update: UpdateName, @PathVariable("id") id: Long): StudentEntity =
        service.updateName(update, id)

    @DeleteMapping
    fun deleteAllStudentInfo(): String {
        service.deleteAll()
        return "Delete Done"
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun deleteOneStudentInfo(@PathVariable("id") id: Long): String {
        service.deleteOne(id)
        return "Delete $id Done"
    }


}