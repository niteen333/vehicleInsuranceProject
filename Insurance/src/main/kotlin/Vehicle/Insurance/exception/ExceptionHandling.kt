package Vehicle.Insurance.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import Vehicle.Insurance.exception.ErrorMessage as ErrorMessage1

@ControllerAdvice
class ExceptionHandling {
    //main class for exception
    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorMessage1> {

        val errorMessage = ErrorMessage1(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleUserServiceException(ex: UserIdException): ResponseEntity<ErrorMessage1> {
        val errorMessage = ErrorMessage1(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<ErrorMessage1> {
        val errorMessage = ErrorMessage1(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }


    @ExceptionHandler
    fun handleInsuranceNotFoundException(ex: InsuranceNotFoundException): ResponseEntity<ErrorMessage1> {
        val errorMessage = ErrorMessage1(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}