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
    lateinit var professorRepository: ProfessorRepository


    fun addNew(request: InfoRequest): ProfessorEntity = professorRepository.save(
        ProfessorEntity(

            professorName = "${request.title} ${request.firstName} ${request.lastName}"
        )
    )

    fun updateName(update: UpdateName, id: Long): ProfessorEntity {
        val professorData: ProfessorEntity = professorRepository.findById(id).get()
        professorData.professorName = update.name
        return professorRepository.save(professorData)
    }

    fun getAll(): Iterable<ProfessorEntity> = professorRepository.findAll()

    fun getOne(id: Long): ProfessorEntity = professorRepository.findById(id).get()

    fun deleteAll() = professorRepository.deleteAll()

    fun deleteOne(id: Long) = professorRepository.deleteById(id)

    fun existProfessor(id: Long): Boolean = professorRepository.existsById(id)
}