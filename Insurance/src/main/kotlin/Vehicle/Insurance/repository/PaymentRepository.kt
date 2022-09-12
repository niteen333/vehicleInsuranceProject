package Vehicle.Insurance.repository

import Vehicle.Insurance.model.Payment
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PaymentRepository : ReactiveMongoRepository<Payment, String> {



}