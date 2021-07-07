package com.phat.testapi.controller

import com.phat.testapi.model.entity.ClassroomEntity
import com.phat.testapi.model.entity.ProfessorEntity
import com.phat.testapi.sevices.ClassroomService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

internal class ClassroomControllerTest {
    private lateinit var mockMvc: MockMvc
    private lateinit var classroomController: ClassroomController
    private val classroomService: ClassroomService = mockk()
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
        classroomController = ClassroomController(classroomService)
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController)
            .build()
    }

    /**
     * validate name
     * validate thai id
     * validate surname
     * validate age
     * validate  ....
     *
     *
     * group validate
     * RequestValidateServie {
     *  function validate(name,thaiid,surname, age, ...): boolean {
     *     validate name
     *     validate thai id
     *      validate surname
     *     validate age
     *     validate  ....
     *    return boolean
     *  }
     *
     *  controller {
     *   validateservice
     *
     *   func {
     *    valiate.valid( .... )
     *   }
     *
     *  }
     *
     *  test controller {
     *    fakeValidateService
     *
     *    fun test(){
     *     mock { fakeValidateService.valid ()} return true
     *     expected ...
     *
     *    }
     *  }
     * }
     */
    @Test
    fun `get class room success`() {
        // stubbing get class room
        every { classroomService.getAll() } returns arrayListOf(
            classOne, classTwo
        )

        mockMvc.perform(
            MockMvcRequestBuilders.get("/classroom/")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """[
   {
      "classId":1,
      "className":"Math",
      "professor":{
         "professorId":1,
         "professorName":"Mr.A",
         "postDate":null,
         "updateDate":null,
         "version":1
      },
      "postDate":null,
      "updateDate":null,
      "version":1
   },
   {
      "classId":2,
      "className":"Math 2",
      "professor":{
         "professorId":1,
         "professorName":"Mr.A",
         "postDate":null,
         "updateDate":null,
         "version":1
      },
      "postDate":null,
      "updateDate":null,
      "version":1
   }
]"""
                )
            )

    }

    @Test
    fun `add class success`() {
        every { classroomService.addClass(any()) } returns mockk()
        mockMvc.perform(
            MockMvcRequestBuilders.post("/classroom/")
                .content(
                    """
                    { "classId": 3, "className": "English for comunication", "professorId": 2}
                """.trimIndent()
                )
                .header("X-Correlation-Id", "X-Correlation-Id")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """{"message":"ok"}"""
                )
            )
    }

    @Test
    fun `show class room info by id should be response http status ok and success response`() {
        // stubging
        every { classroomService.getOneClass(1) } returns classOne

        mockMvc.perform(
            MockMvcRequestBuilders.get(
                "/classroom/1"
            )
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().json(
                    """{"classId":1,"className":"Math","professor":{"professorId":1,"professorName":"Mr.A","postDate":null,"updateDate":null,"version":1},"postDate":null,"updateDate":null,"version":1}"""
                )
            )
    }
}