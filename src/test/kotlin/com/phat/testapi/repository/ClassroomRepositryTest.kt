package com.phat.testapi.repository

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.entity.ProfessorEntity
import io.mockk.junit5.MockKExtension
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner


@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClassroomRepositryTest @Autowired constructor(val classroomRepository: ClassroomRepository) {

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


    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeALl() {
            println("before all")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("after all")
        }

//        @JvmStatic
//        @BeforeClass
//        fun beforeClass() {
//            println("before class")
//        }
//
//        @JvmStatic
//        @AfterClass
//        fun afterClass() {
//            println("after class")
//        }

    }


    @BeforeEach
    fun initUseCase() {
        println("before each")
        /* pre-condition */
        val classRooms: List<ClassroomEntity> = arrayListOf(
            classOne
        )
        classroomRepository.saveAll(classRooms)
    }

    @AfterEach
    fun destroyAll() {
        println("after each")
        classroomRepository.deleteAll()
    }

    @Test
    //   <objective>_<pass or fail>
    fun `saveAll_success`() {
        // test step
        // arrange
        // test data
        val classes: List<ClassroomEntity> = arrayListOf(
            classTwo
        )
        // act
        val classesRooms /* actual result */ = classroomRepository.saveAll(classes)
        // assert
        Assertions.assertTrue(profA.professorName == classesRooms.first {
            it.classId == 2L
        }.professor?.professorName /* expected */)
    }

    @Test
    fun `findAll_success`() {
        // arrange
        classroomRepository.save(classTwo)
        Assertions.assertIterableEquals(
            /* act */
            classroomRepository.findAll(),
            /* assert */
            arrayListOf(
                classOne, classTwo
            )
        )
    }
}