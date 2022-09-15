package Vehicle.Insurance.controller

import Vehicle.Insurance.model.User
import Vehicle.Insurance.model.Vehicle
import Vehicle.Insurance.service.UserService
import Vehicle.Insurance.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/vehicle")
class VehicleController (
    @Autowired
    val vehicleService: VehicleService
) {
    @GetMapping("/vehicles/list")

    fun getAllVehicles(): Flux<Vehicle> {
        return vehicleService.findAllVehicles()
    }
}