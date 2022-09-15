package com.kotlinblog.controller

import com.kotlinblog.constaant.RestConstants
import com.kotlinblog.domain.Post
import com.kotlinblog.request.PostCreate
import com.kotlinblog.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(RestConstants.POST_URI)
class PostController(
    private val postService: PostService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun post(
        @RequestBody @Valid request: PostCreate,
    ): Map<String, String?> {
        logger.debug("title: ${request.title}, content: ${request.content}")

        postService.write(request)

        return mapOf()
    }

    @GetMapping("/{postId}")
    fun get(@PathVariable postId: Long): Post {
        return postService.get(postId)
    }

}