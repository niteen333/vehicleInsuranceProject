package Vehicle.Insurance.service

import Vehicle.Insurance.model.Payment
import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.PaymentRepository
import Vehicle.Insurance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PaymentService (
    @Autowired
    val paymentRepository: PaymentRepository
) {
    fun findAllPayments(): Flux<Payment> {
        return paymentRepository.findAll()
    }


    fun addPayment(payment: Payment): Mono<Payment> {
        return paymentRepository.save(payment)

    }
}