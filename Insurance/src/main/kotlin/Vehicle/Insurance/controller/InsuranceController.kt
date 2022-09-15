package Vehicle.Insurance.controller

import Vehicle.Insurance.model.Insurance
import Vehicle.Insurance.model.User
import Vehicle.Insurance.service.InsuranceService
import Vehicle.Insurance.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/insurance")
class InsuranceController (
    @Autowired
    val insuranceService: InsuranceService
)
{
    @GetMapping("/insurance/list")

    fun getAllInsurances(): Flux<Insurance> {
        return insuranceService.findAllInsurance()
    }
    @PostMapping("/insurance/add")
    fun createInsurance(@RequestBody insurance: Insurance): Mono<Insurance> {
        return insuranceService.addInsurance(insurance)
    }

    @PutMapping("/update/{id}")
    fun updateInsuranceById(@PathVariable("id") id: String, @RequestBody insurance: Insurance): Mono<Insurance> {
        return insuranceService.updateInsurance(id, insurance)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteInsuranceById(@PathVariable("id") id: String): Mono<Void> {
        return insuranceService.deleteById(id)
    }

}

