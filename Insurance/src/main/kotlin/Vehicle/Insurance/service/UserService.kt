package Vehicle.Insurance.service

import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService (
    @Autowired
    val userRepository: UserRepository
)

{
    fun findAll() : Flux<User> =
        userRepository.findAll()




    fun addUser(user: User): Mono<User> {
        return userRepository.save(user)

    }
    fun updateUser(id: String, user: User): Mono<User> {
        return userRepository.findById(id)
            .flatMap {
                it.phone_num=user.phone_num

                userRepository.save(it)
            }
    }
    fun deleteById(id: String): Mono<Void> {
        return userRepository.deleteById(id)
    }




}