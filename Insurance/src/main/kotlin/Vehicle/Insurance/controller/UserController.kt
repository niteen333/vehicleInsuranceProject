package Vehicle.Insurance.controller

import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import Vehicle.Insurance.repository.VehicleRepository
import Vehicle.Insurance.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono



@CrossOrigin(origins = ["http://localhost:3000"], maxAge=3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/v1")
class UserController (
    @Autowired
    val userRepository: UserRepository,
    val userService: UserService
)
{
    @GetMapping("/users/list")

    fun getAllUsers(): Flux<User> {
        return userService.findAll()
    }




    @PostMapping("/users/add")
    fun createUser(@RequestBody user: User): Mono<User> {
        return userService.addUser(user)
    }

    @PutMapping("/user/update/{id}")
    fun updateUserById(@PathVariable("id") id: String, @RequestBody user: User): Mono<User> {
        return userService.updateUser(id, user)
    }
    @DeleteMapping("/user/delete/{id}")
    fun deleteUserById(@PathVariable("id") id: String): Mono<Void> {
        return userService.deleteById(id)
    }

}

