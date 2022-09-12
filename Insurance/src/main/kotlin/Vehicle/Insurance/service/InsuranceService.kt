package Vehicle.Insurance.service

import Vehicle.Insurance.model.Insurance
import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.InsuranceRepository
import Vehicle.Insurance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class InsuranceService (
    @Autowired
    val insuranceRepository: InsuranceRepository
)

{
    fun findAllInsurance() : Flux<Insurance> {
        return insuranceRepository.findAll()
    }

    fun addInsurance(insurance: Insurance): Mono<Insurance> {
        return insuranceRepository.save(insurance)

    }
    fun updateInsurance(id: String, insurance: Insurance): Mono<Insurance> {
        return insuranceRepository.findById(id)
            .flatMap {
                it.insuranceNumber=insurance.insuranceNumber

                insuranceRepository.save(it)
            }
    }
    fun deleteById(id: String): Mono<Void> {
        return insuranceRepository.deleteById(id)
    }
}