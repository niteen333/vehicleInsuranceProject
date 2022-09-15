package Vehicle.Insurance.controller

import Vehicle.Insurance.model.InsuranceBoking
import Vehicle.Insurance.model.Payment
import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.PaymentRepository
import Vehicle.Insurance.repository.UserRepository
import Vehicle.Insurance.service.PaymentService
import Vehicle.Insurance.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1")
class PaymentController (
    @Autowired
    val paymentRepository: PaymentRepository,
    val paymentService: PaymentService
) {
    @GetMapping("/list")

    fun getAllPayments(): Flux<Payment> {
        return paymentService.findAllPayments()
    }
    @PostMapping("/add")
    fun createPayment(@RequestBody payment: Payment): Mono<Payment> {
        return paymentService.addPayment(payment)
    }
}