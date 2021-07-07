package com.phat.testapi.repository

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.entity.ProfessorEntity
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@ExtendWith(MockKExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClassroomRepositryTest @Autowired constructor(val classroomRepository: ClassroomRepository) {

    private val profA = ProfessorEntity(
        1,
        "Mr.A"
    )

    @BeforeEach
    fun initUseCase() {
        val classRooms: List<ClassroomEntity> = arrayListOf(
            ClassroomEntity(
                1, "Math", profA
            )
        )
        classroomRepository.saveAll(classRooms)
    }

    @AfterEach
    fun destroyAll() {
        classroomRepository.deleteAll()
    }

    @Test
    fun saveAll_success() {
        val classes: List<ClassroomEntity> = arrayListOf(
            ClassroomEntity(
                2, "Math 2", profA
            )
        )
        val classesRooms = classroomRepository.saveAll(classes)

        Assertions.assertTrue(profA.professorName == classes.first {
            it.classId == 2L
        }.professor?.professorName)
    }
}