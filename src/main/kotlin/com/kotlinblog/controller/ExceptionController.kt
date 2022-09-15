package com.kotlinblog.controller

import com.kotlinblog.constaant.RestConstants
import com.kotlinblog.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidRequestHandler(
        e: MethodArgumentNotValidException
    ): ErrorResponse {
        logger.info("invalidRequestHandler: ${e.message}")

        return if (e.hasErrors()) {
            ErrorResponse(
                code = RestConstants.BAD_REQUEST_CODE,
                message = RestConstants.DEFAULT_ERROR_MESSAGE,
                validation = e.fieldErrors.associate {
                    it.field to it.defaultMessage
                }
            )
        } else {
            ErrorResponse()
        }
    }
}