package com.erikschouten.customclasses.validators

import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import java.text.Normalizer
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention
@Constraint(validatedBy = [NoHtmlValidator::class])
annotation class NoHtml(val message: String = "Html was found in string",
                        val groups: Array<KClass<*>> = [],
                        val payload: Array<KClass<out Payload>> = [])

class NoHtmlValidator : ConstraintValidator<NoHtml, String> {
  override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
    val normalizedHtml = Normalizer.normalize(value, Normalizer.Form.NFKC)

    return Jsoup.isValid(normalizedHtml, Whitelist.none())
  }
}
