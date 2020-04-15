package com.erikschouten.customclasses.validators

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention
@Constraint(validatedBy = [HexColorNonTransparentValidator::class])
annotation class HexColorNonTransparent(val message: String = "Invalid HEX color code",
                          val groups: Array<KClass<*>> = [],
                          val payload: Array<KClass<out Payload>> = [])

class HexColorNonTransparentValidator : ConstraintValidator<HexColorNonTransparent, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        return value.matches(Regex("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})\$"))
    }
}
