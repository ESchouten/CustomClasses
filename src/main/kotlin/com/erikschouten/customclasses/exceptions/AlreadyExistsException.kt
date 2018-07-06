package com.erikschouten.customclasses.exceptions

class AlreadyExistsException(override var message: String) : RuntimeException(message)
