package com.erikschouten.customclasses.validators

import org.junit.Before
import org.junit.Test
import javax.validation.Validation
import javax.validation.Validator

class ValidatorTests {

    private lateinit var validator: Validator

    @Before
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun nonTransparentHEXColor() {
        var violations = validator.validate(ColorObj("#fff"))
        assert(violations.isEmpty())
        violations = validator.validate(ColorObj("#FFF"))
        assert(violations.isEmpty())
        violations = validator.validate(ColorObj("#FAFAFA"))
        assert(violations.isEmpty())
        violations = validator.validate(ColorObj("#Fa9AfA"))
        assert(violations.isEmpty())

        violations = validator.validate(ColorObj("#Fa9AfAF"))
        assert(violations.size == 1)
        violations = validator.validate(ColorObj("#Fa9Af"))
        assert(violations.size == 1)
        violations = validator.validate(ColorObj("#FAFAFG"))
        assert(violations.size == 1)
    }

    @Test
    fun email() {
        //Valid
        var violations = validator.validate(EmailObj("e@e.nl"))
        assert(violations.isEmpty())

        //Valid
        violations = validator.validate(EmailObj("@"))
        assert(violations.isEmpty())

        violations = validator.validate(EmailObj("vdnskl.a"))
        assert(violations.size == 1)
    }

    @Test
    fun noHtml() {
        //Valid
        var violations = validator.validate(Obj("S", listOf("S"), mapOf(Pair("S", "S"))))
        assert(violations.isEmpty())

        violations = validator.validate(Obj("S"))
        assert(violations.isEmpty())

        violations = validator.validate(Obj(string = "<script src=http://www.example.com/malicious-code.js></script>"))
        assert(violations.size == 1)

        violations = validator.validate(Obj(list = listOf("<script src=http://www.example.com/malicious-code.js></script>")))
        assert(violations.size == 1)

        violations = validator.validate(Obj(map = mapOf(Pair("<script src=http://www.example.com/malicious-code.js></script>", "s"))))
        assert(violations.size == 1)

        violations = validator.validate(Obj(map = mapOf(Pair("s", "<script src=http://www.example.com/malicious-code.js></script>"))))
        assert(violations.size == 1)

        violations = validator.validate(Obj("s", listOf("s"), mapOf(Pair("s", "s"))))
        assert(violations.isEmpty())
    }

    @Test
    fun password() {
        var violations = validator.validate(Pwd("aaAA22!!"))
        assert(violations.size == 0)
        violations = validator.validate(Pwd("aaAA2!!"))
        assert(violations.size == 1)
        violations = validator.validate(Pwd("aaAA2222"))
        assert(violations.size == 1)
    }

    data class Obj(@field:[NoHtml] val string: String? = null,
                   @field:[NoHtml] val list: List<String>? = null,
                   @field:[NoHtml] val map: Map<String, String>? = null)

    data class Str(@field:[NoHtml] val string: String,
                   @field:[NoHtml] val list: List<String>,
                   @field:[NoHtml] val map: Map<String, String>)

    data class EmailObj(@field:[Email] val value: String)

    data class Pwd(@field:[Password] val password: String)

    data class ColorObj(@field:[HexColorNonTransparent] val color: String)
}
