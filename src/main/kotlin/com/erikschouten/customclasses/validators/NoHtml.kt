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
@Constraint(validatedBy = [NoHtmlValidator::class, NoHtmlListValidator::class, NoHtmlMapValidator::class])
annotation class NoHtml(val message: String = "Html was found in string",
                        val groups: Array<KClass<*>> = [],
                        val payload: Array<KClass<out Payload>> = [])

class NoHtmlValidator : ConstraintValidator<NoHtml, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val normalizedHtml = Normalizer.normalize(value, Normalizer.Form.NFKC)

        return Jsoup.isValid(normalizedHtml, Whitelist.none())
    }
}

class NoHtmlListValidator : ConstraintValidator<NoHtml, Collection<String>> {
    override fun isValid(collection: Collection<String>?, context: ConstraintValidatorContext?): Boolean {
        if (collection == null) return true
        for (value in collection) {
            val normalizedHtml = Normalizer.normalize(value, Normalizer.Form.NFKC)
            if (!Jsoup.isValid(normalizedHtml, Whitelist.none())) return false
        }

        return true
    }
}

class NoHtmlMapValidator : ConstraintValidator<NoHtml, Map<String, String>> {
    override fun isValid(map: Map<String, String>?, context: ConstraintValidatorContext?): Boolean {
        if (map == null) return true
        for (entry in map.entries) {
            var normalizedHtml = Normalizer.normalize(entry.key, Normalizer.Form.NFKC)
            if (!Jsoup.isValid(normalizedHtml, Whitelist.none())) return false
            normalizedHtml = Normalizer.normalize(entry.value, Normalizer.Form.NFKC)
            if (!Jsoup.isValid(normalizedHtml, Whitelist.none())) return false
        }
        return true
    }
}
