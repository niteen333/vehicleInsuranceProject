package Vehicle.Insurance.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "ins")
data class Insurance (
    var insuranceNumber:String
)