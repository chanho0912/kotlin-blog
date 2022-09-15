package com.kotlinblog.request

import com.kotlinblog.constaant.ValidationConstants
import javax.validation.constraints.NotBlank

data class PostCreate(
    @field:NotBlank(message = ValidationConstants.NOT_BLANK_TITLE)
    val title: String,

    @field:NotBlank(message = ValidationConstants.NOT_BLANK_CONTENT)
    val content: String,
)