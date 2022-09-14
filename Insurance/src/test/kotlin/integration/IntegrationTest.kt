package com.VehicleInsurance.integration


import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import io.kotlintest.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
class IntegrationTests {


    @Autowired
    lateinit var client : WebTestClient

    @Autowired
    lateinit var userRepository : UserRepository

    @BeforeEach
    fun setUp(){
        userRepository.deleteAll().subscribe()
    }

    @Test
    fun `should return list of users from database`(){

        val expectedResponse =
            mapOf("id" to 1,
                "first_name" to "nitin",
                "last_name" to "pawar",
                "email" to "nitin@gmail",
                "phone_num" to "8793471790",
                "password" to "123",
            )
        val user1 = User("1","nitin" ,"pawar" , "nitin@gmail", 8793471790, "123")



        val response = client.post()
            .uri("/api/v1/users/lists")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody.blockFirst()

        response shouldBe expectedResponse
        //response.blockLast() shouldBe expectedResult[1]

    }
}