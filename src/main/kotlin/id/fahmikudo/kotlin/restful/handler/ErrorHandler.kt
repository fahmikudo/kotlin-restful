package id.fahmikudo.kotlin.restful.handler

import id.fahmikudo.kotlin.restful.model.BaseResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): BaseResponse<String> {
        return BaseResponse<String>(
            code = HttpStatus.BAD_REQUEST.value(),
            status = HttpStatus.BAD_REQUEST.name,
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [ResponseStatusException::class])
    fun clientHandler(responseStatusException: ResponseStatusException): BaseResponse<String> {
        return BaseResponse<String>(
            code = responseStatusException.statusCode.value(),
            status = HttpStatus.valueOf(responseStatusException.statusCode.value()).reasonPhrase,
            data = responseStatusException.reason!!
        )
    }

}