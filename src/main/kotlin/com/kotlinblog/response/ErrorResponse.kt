package com.kotlinblog.response

import com.kotlinblog.constaant.RestConstants

data class ErrorResponse(
    val code: String = RestConstants.BAD_REQUEST_CODE,
    val message: String = RestConstants.DEFAULT_ERROR_MESSAGE,
    val validation: Map<String, String?> = mapOf(),
)