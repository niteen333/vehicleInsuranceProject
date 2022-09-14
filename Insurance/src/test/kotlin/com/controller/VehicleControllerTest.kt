package com.VehicleInsurance.com.controller







import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import Vehicle.Insurance.service.UserService
import com.controller.UserController
import Vehicle.Insurance.model.Vehicle
import Vehicle.Insurance.repository.VehicleRepository
import Vehicle.Insurance.service.VehicleService
import com.controller.VehicleController
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




@WebFluxTest(VehicleController::class)
@AutoConfigureWebTestClient
class VehicleControllerTest {
    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var vehicleService: VehicleService

    @Autowired
    lateinit var vehicleRepository: VehicleRepository


    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun vehicleService() = mockk<VehicleService>()


        @Bean
        fun vehicleRepository() = mockk<VehicleRepository>()
    }


    @Test
    fun `should return list of vehicle from Database`() {
        //arrange the data member ---AAA
        val vehicle1 = Vehicle(
            vehicleID = "1",
            vehicleName = "creta",
            vehicleNo = 123456789,
            vehiclebrand = "hundai",
            rcno = 123456789,
            city = "jalgaon", state = "maharashtra"

        )
        val vehicle2 = Vehicle(
            vehicleID = "2",
            vehicleName = "i10",
            vehicleNo = 987654321,
            vehiclebrand = "hundai",
            rcno = 123458589,
            city = "jalgaon", state = "maharashtra"

        )

        val expectedResult = listOf(
            mapOf(
                "vehicleID" to "1",
                "vehicleName" to "creta",
                "vehicleNo" to 123456789,
                "vehiclebrand" to "hundai",
                "rcno" to 123456789,
                "city" to "jalgaon ",
                "state" to "maharashtra"
            ),
            mapOf(
                "vehicleID" to "2",
                "vehicleName" to "i10",
                "vehicleNo" to 987654321,
                "vehiclebrand" to "hundai",
                "rcno" to 123458589,
                "city" to "jalgaon", "state" to "maharashtra"
            ),

            )

        //Action invoking ---AAA

        every {
            vehicleService.findAllVehicles()
        } returns Flux.just(vehicle1, vehicle2)


        //Assertion
        val response = client.get()
            .uri("/vehicle/vehicles/list")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            vehicleService.findAllVehicles()

        }
    }
}





