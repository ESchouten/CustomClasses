package com.erikschouten.customclasses.exceptions

class InvalidDataException(override var message: String) : RuntimeException(message)
