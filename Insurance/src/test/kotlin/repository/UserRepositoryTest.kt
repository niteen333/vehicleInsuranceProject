package com.VehicleInsurance.repository




import Vehicle.Insurance.model.User
import Vehicle.Insurance.repository.UserRepository
import io.kotlintest.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles

@DataMongoTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun tearDown() {
        userRepository.deleteAll().subscribe()
    }

    @Test
    fun `should find User for given id`() {

        val user = User( user_id = "1",
            first_name = "nitin",
            last_name = "pawar",
            email = "nitin@gmail",
            phone_num = 8793471790,
            password = "123")

        userRepository.save(user).block()

        val actualSaveApplication = userRepository.findById("1"
        ).block()

        if (actualSaveApplication != null) {
            actualSaveApplication shouldBe user
        }
    }



}