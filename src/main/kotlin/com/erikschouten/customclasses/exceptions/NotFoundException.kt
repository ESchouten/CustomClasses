package com.erikschouten.customclasses.exceptions

class NotFoundException(override var message: String) : RuntimeException(message)
