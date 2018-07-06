package com.erikschouten.customclasses.exceptions

class InvalidParameterException(override var message: String) : RuntimeException(message)
