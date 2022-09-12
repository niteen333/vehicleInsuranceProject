package Vehicle.Insurance.repository

import Vehicle.Insurance.model.Insurance
import Vehicle.Insurance.model.Vehicle
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface InsuranceRepository: ReactiveMongoRepository<Insurance, String> {

}