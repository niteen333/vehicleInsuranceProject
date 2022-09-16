package com.controller

import Vehicle.Insurance.controller.PaymentController
import Vehicle.Insurance.model.Payment
import Vehicle.Insurance.repository.PaymentRepository
import Vehicle.Insurance.service.PaymentService


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


@WebFluxTest(PaymentController::class)
@AutoConfigureWebTestClient
class PaymentControllerTest {
    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var paymentService: PaymentService

    @Autowired
    lateinit var paymentRepository: PaymentRepository


    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun paymentService() = mockk<PaymentService>()


        @Bean
        fun paymentRepository() = mockk<PaymentRepository>()
    }


    @Test
    fun `should return list of payment from Database`() {
        //arrange the data member ---AAA
        val payment1 = Payment(
            cardno = 123456789,
            nameoncard = "nitin",
            cvv = 123

        )


        val expectedResult = listOf(
            mapOf(
                " cardno" to 123456789,
                "nameoncard" to "nitin",
                "cvv" to 123
            )
        )

        //Action invoking ---AAA

        every {
            paymentService.findAllPayments()
        } returns Flux.just(payment1)


        //Assertion
        val response = client.get()
            .uri("/api/v1/list")
            .accept(MediaType.APPLICATION_JSON)
            .exchange() //invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult

        verify(exactly = 1) {
            paymentService.findAllPayments()

        }
    }





@Test
fun `should add the Payment in database when createPaymenet called`() {


    val payment1 = Payment(
        cardno = 123456789,
        nameoncard = "nitin",
        cvv = 123

    )


    val expectedResult = listOf(
        mapOf(
            " cardno" to 123456789,
            "nameoncard" to "nitin",
            "cvv" to 123
        )
    )

    //Action invoking ---AAA

    every {
        paymentService.addPayment(payment1)
    } returns Mono.just(payment1)


    //Assertion
    val response = client.post()
        .uri("/api/v1/add")
        .accept(MediaType.APPLICATION_JSON)
        .exchange() //invoking the end point
        .expectStatus().is2xxSuccessful
        .returnResult<Any>()
        .responseBody

    response.blockFirst() shouldBe expectedResult

    verify(exactly = 1) {
        paymentService.addPayment(payment1)

    }
}






















}





