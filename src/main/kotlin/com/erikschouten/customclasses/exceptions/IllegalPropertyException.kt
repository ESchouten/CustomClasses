package com.erikschouten.customclasses.exceptions

class IllegalPropertyException(override var message: String) : RuntimeException(message)