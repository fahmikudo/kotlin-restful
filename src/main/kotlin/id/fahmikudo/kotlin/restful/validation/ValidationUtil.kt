package id.fahmikudo.kotlin.restful.validation

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.springframework.stereotype.Component

@Component
class ValidationUtil (val validator: Validator) {

    fun validate(any: Any) {
        val result = validator.validate(any)
        if (result.isNotEmpty()) {
            throw ConstraintViolationException(result)
        }
    }

}