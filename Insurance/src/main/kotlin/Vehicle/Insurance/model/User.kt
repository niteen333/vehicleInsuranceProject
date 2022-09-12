package Vehicle.Insurance.model

import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime


@Document
data class User(

    @Id
    var user_id: String?,
    var first_name: String?,
    var last_name: String?,
    var email: String?,
    var phone_num:Long?,
    var password:String?,
   // var vehicle: Vehicle?


)


