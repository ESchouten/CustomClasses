package com.erikschouten.customclasses.exceptions.handling

import java.lang.Exception

fun getMessage(objectName: String?, field: String?, status: Int?, message: String?): String {
    var exception = ""
    if (status != null) exception += "$status: "
    if (objectName != null) exception += "$objectName."
    if (field != null) exception += "$field"
    if (message != null) exception += " | $message"

    return exception
}

class FieldErrorException(
    val objectName: String? = null,
    val field: String? = null,
    val status: Int? = null,
    val description: String? = null
) : Exception(getMessage(objectName, field, status, description)) {

    override fun toString() = getMessage(objectName, field, status, description)
}

