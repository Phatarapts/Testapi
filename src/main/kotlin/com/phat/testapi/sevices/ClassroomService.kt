package com.phat.testapi.sevices

import com.phat.testapi.model.entity.ClassroomEntity

import com.phat.testapi.model.request.ClassroomRequest

import com.phat.testapi.repository.ClassroomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClassroomService {
    @Autowired
    lateinit var repository: ClassroomRepository

    fun addClass(request: ClassroomRequest): ClassroomEntity = repository.save(
        ClassroomEntity(
            classId = request.classId,
            className = request.className,
            professorId = request.professorId
        )
    )

    fun updateClassName(update: ClassroomRequest, id: Long): ClassroomEntity {
        val studentData: ClassroomEntity = repository.findById(id).get()
        studentData.className = update.className
        return repository.save(studentData)
    }

    fun getAll(): Iterable<ClassroomEntity> = repository.findAll()
    fun getOneClass(id: Long): ClassroomEntity = repository.findById(id).get()
    fun deleteAll() = repository.deleteAll()
    fun deleteOne(id: Long) = repository.deleteById(id)
}