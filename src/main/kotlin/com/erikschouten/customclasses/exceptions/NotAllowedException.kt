package com.erikschouten.customclasses.exceptions

class NotAllowedException(override var message: String) : RuntimeException(message)
