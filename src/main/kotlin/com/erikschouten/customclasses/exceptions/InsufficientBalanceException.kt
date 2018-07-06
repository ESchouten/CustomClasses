package com.erikschouten.customclasses.exceptions

class InsufficientBalanceException(override var message: String) : RuntimeException(message)
