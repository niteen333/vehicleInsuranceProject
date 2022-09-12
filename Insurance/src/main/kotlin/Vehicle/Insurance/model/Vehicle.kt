package Vehicle.Insurance.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "vehicle")
data class Vehicle (
    @Id
    var vehicleID: String?,
    var vehicleName: String?,
    var vehicleNo: Long?,
    var vehiclebrand:String?,
    var rcno:Long?,
    var city:String?,
    var state:String?


    // var insurance:Insurance

)
