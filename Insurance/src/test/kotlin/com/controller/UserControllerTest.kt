package com.VehicleInsurance.com.controller





import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import Vehicle.Insurance.service.UserService
import Vehicle.Insurance.controller.UserController
import io.kotlintest.shouldBe

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@WebFluxTest(UserController::class)
@AutoConfigureWebTestClient
class UserControllerTest {
    @Autowired
    lateinit var client : WebTestClient

    @Autowired
    lateinit var userService : UserService
    @Autowired
    lateinit var userRepository: UserRepository


    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun userService() = mockk<UserService>()


        @Bean
        fun userRepository() = mockk<UserRepository>()
    }



    @Test
    fun `should return list of users`(){

        val user1 = User(
            user_id = "1",
            first_name = "nitin",
            last_name = "pawar",
            email = "nitin@gmail",
            phone_num = 8793471790,
            password = "123"

        )

        val expectedResult = listOf(
            mapOf("id" to 1,
                "first_name" to "nitin",
                "last_name" to "pawar",
                "email" to "nitin@gmail",
                "phone_num" to 8793471790,
                "password" to "123",
            ),
            every {
                userService.findAll()
            } returns Flux.just(user1)


        )
        val response = client.get()
            .uri("/api/v1/users/list")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            userService.findAll()

        }
    }
    @Test
    fun `should be able to update the user`() {

        val expectedResult =mapOf(

            "id" to "1",
            "first_name" to "nitin",
            "last_name" to "pawar",
            "email" to "nitin@gmail",
            "phone_num" to 8793471790,
            "password" to "123",
            )

        val user = User(
             "1",
            "nitin",
            "pawar",
            "nitin@gmail",
             8793471790,
             "123"

        )
                    every {
            userService.updateUser("1",user)
        } returns Mono.just(user)

        val response = client.put()
            .uri("/user/update/1")
            .bodyValue(user)
            .exchange()
            .expectStatus().is2xxSuccessful

        verify(exactly = 1) {
            userService.updateUser("1",user)
        }
    }




}

