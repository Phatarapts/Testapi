package com.phat.testapi.exception

import java.lang.RuntimeException

data class NotFoundDataException(override val message: String?) : RuntimeException()