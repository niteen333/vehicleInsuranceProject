package Vehicle.Insurance.controller.controller







import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import Vehicle.Insurance.service.UserService
import Vehicle.Insurance.controller.controller.UserController
import Vehicle.Insurance.model.Vehicle
import Vehicle.Insurance.repository.VehicleRepository
import Vehicle.Insurance.service.VehicleService
import io.kotlintest.shouldBe

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import net.bytebuddy.matcher.ElementMatchers.returns
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




@WebFluxTest(UserController::class)
@AutoConfigureWebTestClient
class VehicleControllerTest {
    @Autowired
    lateinit var client : WebTestClient

    @Autowired
    lateinit var userService : UserService
    @Autowired
    lateinit var userRepository: UserRepository


    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun vehicleService() = mockk<VehicleService>()


        @Bean
        fun  vehicleRepository() = mockk<VehicleRepository>()
    }



    @Test
    fun `should return list of users`(){

        val vehicle1 = Vehicle(
            vehicleID ="1",
         vehicleName ="creta",
         vehicleNo = 2025,
         vehiclebrand="hundai",
         rcno =123456789,
         city ="jalgaon",  state ="maharashtra"

        )

        val expectedResult = listOf(
            mapOf(vehicleID to "1",
                vehicleName to "creta",
                vehicleNo to 2025,
                vehiclebrand to "hundai",
                rcno to 123456789
                city to"jalgaon ",
            state to "maharashtra",
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



}

