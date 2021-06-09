package com.phat.testapi.sevices

import com.phat.testapi.model.entity.ProfessorEntity
import com.phat.testapi.model.request.InfoRequest
import com.phat.testapi.model.request.UpdateName
import com.phat.testapi.repository.ProfessorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfessorService {
    @Autowired
    lateinit var repository: ProfessorRepository

    fun addNew(request: InfoRequest): ProfessorEntity = repository.save(
        ProfessorEntity(
            classId = request.classroom,
            professorName = "${request.title} ${request.firstName} ${request.lastName}"
        )
    )

    fun updateName(update: UpdateName, id: Long): ProfessorEntity {
        val professorData: ProfessorEntity = repository.findById(id).get()
        professorData.professorName = update.name
        return repository.save(professorData)
    }

    fun getAll(): Iterable<ProfessorEntity> = repository.findAll()

    fun getOne(id: Long): ProfessorEntity = repository.findById(id).get()

    fun deleteAll() = repository.deleteAll()

    fun deleteOne(id: Long) = repository.deleteById(id)
}