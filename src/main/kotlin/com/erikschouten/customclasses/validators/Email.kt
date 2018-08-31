package com.erikschouten.customclasses.validators

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention
@Constraint(validatedBy = [EmailValidator::class])
annotation class Email(val message: String = "Invalid email",
                          val groups: Array<KClass<*>> = [],
                          val payload: Array<KClass<out Payload>> = [])

class EmailValidator : ConstraintValidator<Email, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        return value.contains('@')
    }
}
