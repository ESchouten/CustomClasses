package com.erikschouten.customclasses.exceptions

class ExternalConflictException(override var message: String) : RuntimeException(message)