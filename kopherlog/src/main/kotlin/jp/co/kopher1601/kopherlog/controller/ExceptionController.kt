package jp.co.kopher1601.kopherlog.controller

import jp.co.kopher1601.kopherlog.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidRequestHandler(e: MethodArgumentNotValidException): ErrorResponse {
        val response = ErrorResponse("400", "잘못된 요청입니다.")

        for (fieldError in e.fieldErrors) {
            response.addValidation(fieldError.field, fieldError.defaultMessage ?: "")
        }

        return response
    }
}