package com.erikschouten.customclasses.validators

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention
@Constraint(validatedBy = [PasswordValidator::class])
annotation class Password(val message: String = "Invalid password",
                          val groups: Array<KClass<*>> = [],
                          val payload: Array<KClass<out Payload>> = [])

class PasswordValidator : ConstraintValidator<Password, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        return value.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\$@\$!%*?&])[A-Za-z\\d\$@\$!%*?&]{8,}"))
    }
}
