package Vehicle.Insurance.repository

import Vehicle.Insurance.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<User, String> {
    abstract fun findByFirst_name(nitin: String): Any


}
