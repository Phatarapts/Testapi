package com.phat.testapi.sevices

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.entity.ProfessorEntity
import com.phat.testapi.repository.ClassroomRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ClassroomServiceTest {
    private lateinit var classroomService: ClassroomService;

    private val classroomRepository: ClassroomRepository = mockk()
    private val profA = ProfessorEntity(
        1,
        "Mr.A"
    )
    val classOne = ClassroomEntity(
        1, "Math", profA
    )
    val classTwo = ClassroomEntity(
        2, "Math 2", profA
    )

    @BeforeEach
    fun setUp() {
        classroomService = ClassroomService()
        classroomService.repository = classroomRepository
    }

    @Test
    fun `getAll success`() {
        every { classroomRepository.findAll() } returns arrayListOf(
            classOne, classTwo
        )
        val classrooms = classroomService.getAll()

        Assertions.assertIterableEquals(
            classrooms,
            arrayListOf(
                classOne, classTwo
            )
        )
    }
}