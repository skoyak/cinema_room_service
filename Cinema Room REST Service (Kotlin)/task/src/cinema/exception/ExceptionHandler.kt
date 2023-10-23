package cinema.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handle(ex: Exception?,
               request: HttpServletRequest?, response: HttpServletResponse?): ResponseEntity<Any> {

        return when (ex) {
            is NullPointerException -> ResponseEntity(ex, HttpStatus.BAD_REQUEST)
            is BusinessException -> ResponseEntity(ex, HttpStatus.BAD_REQUEST)
            is WrongTokenException -> ResponseEntity(ex, HttpStatus.BAD_REQUEST)
            is WrongPasswordException -> ResponseEntity(ex, HttpStatus.UNAUTHORIZED)
            else -> {
                ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }
}