package Vehicle.Insurance.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Book")
data class InsuranceBoking (
   var booking_id :String?,
   var startDate:String?,
   var endDate:String?,
   var  bookingAmount:String

)