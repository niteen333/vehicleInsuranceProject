package Vehicle.Insurance.repository

import Vehicle.Insurance.model.User
import Vehicle.Insurance.model.Vehicle
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface VehicleRepository: ReactiveMongoRepository<Vehicle, String> {


}