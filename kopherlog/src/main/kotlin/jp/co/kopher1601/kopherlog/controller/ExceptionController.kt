package jp.co.kopher1601.kopherlog.controller

import jp.co.kopher1601.kopherlog.exception.PostNotFound
import jp.co.kopher1601.kopherlog.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFound::class)
    fun postNotFound(e: PostNotFound): ErrorResponse {
        val response = ErrorResponse(code = "400", message = e.message)

        log.info("Error! = {}", response)
        return response
    }
}