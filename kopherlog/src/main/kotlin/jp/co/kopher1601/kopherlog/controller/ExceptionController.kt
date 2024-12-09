package jp.co.kopher1601.kopherlog.controller

import jp.co.kopher1601.kopherlog.exception.KopherlogException
import jp.co.kopher1601.kopherlog.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidRequestHandler(e: MethodArgumentNotValidException): ErrorResponse {
        val response = ErrorResponse("400", "잘못된 요청입니다.")

        for (fieldError in e.fieldErrors) {
            response.addValidation(fieldError.field, fieldError.defaultMessage ?: "")
        }

        return response
    }

    @ExceptionHandler(KopherlogException::class)
    fun postNotFound(e: KopherlogException): ResponseEntity<ErrorResponse> {
        val statusCode = e.statusCode()

        val response = ErrorResponse(
            code = statusCode.toString(),
            message = e.message,
            validationErrors = e.validation
        )

        log.info("Error! = {}", response)
        return ResponseEntity.status(statusCode).body(response)
    }
}
