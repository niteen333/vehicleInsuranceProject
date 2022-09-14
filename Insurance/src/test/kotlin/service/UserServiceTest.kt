package com.VehicleInsurance.service

import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import Vehicle.Insurance.service.UserService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class UserServiceTest {
    // mocking the repository layer response
    val user1 = User( user_id = "1",
        first_name = "nitin",
        last_name = "pawar",
        email = "nitin@gmail",
        phone_num = 8793471790,
        password = "123")

    private val userRepository = mockk<UserRepository>() {

        every {
            save(user1)
        }returns Mono.just(user1)

        every {
            findAll()
        } returns Flux.just(user1)

        every {
            deleteById("1")
        }
    }




    private val userService = UserService(userRepository)


    @Test
    fun `should return users when find  method is called`() {

        val firstUser = userService.findAll().blockFirst()


        if (firstUser != null) {
            firstUser shouldBe user1
        }

    }

    @Test
    fun `should expect on complete call post all the Users are retrieved`() {
        //StepVerifier takes care of subscribing
        StepVerifier.create(userService.findAll()).expectSubscription().expectNext(user1)
            .verifyComplete()
        StepVerifier.create(userService.findAll()).expectNextCount(1).verifyComplete()
    }

    @Test
    fun `should delete the user on the basis of the id`() {

        val result = userService.deleteById("1")

        //result shouldBe user1
    }
}