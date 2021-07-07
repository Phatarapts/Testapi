package com.phat.testapi.sevices

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.entity.ProfessorEntity
import com.phat.testapi.model.request.ClassroomRequest
import com.phat.testapi.repository.ClassroomRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class ClassroomServiceTest {
    private lateinit var classroomService: ClassroomService
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
        // arrange
        val mockResponse = arrayListOf(
            classOne, classTwo
        )
        // stubbing
        every { classroomRepository.findAll() } returns mockResponse

        // act
        val classrooms = classroomService.getAll()

        // assert
        Assertions.assertIterableEquals(
            classrooms,
            arrayListOf(
                classOne, classTwo
            )
        )
    }

    @Test
    fun `add class success`() {
        // arrange
        val expectedResult = ClassroomEntity(
            classId = 100,
            className = "Fake Class",
            professor = ProfessorEntity(
                professorId = 999,
                professorName = "Mr.Bean"
            )
        )
        val classRoomRequest = ClassroomRequest(
            classId = 100,
            className = "Fake Class",
            professorId = 999
        )
        // stubbing
        // provide behavior something to response
        every { classroomRepository.save(any()) } returns ClassroomEntity(
            classId = 100,
            className = "Fake Class",
            professor = ProfessorEntity(
                professorId = 999,
                professorName = "Mr.Bean"
            )
        )


        // act
        val actualResult = classroomService.addClass(classRoomRequest)

        Assertions.assertAll(
            Executable {
                // assert
                Assertions.assertEquals(expectedResult, actualResult)
            },
            Executable {
                // verify that mock is called
                verify(exactly = 1) { classroomRepository.save(any()) }
            }
        )
    }
}