package com.kotlinblog.controller

import com.kotlinblog.constaant.RestConstants
import com.kotlinblog.request.PostCreate
import com.kotlinblog.response.PostResponse
import com.kotlinblog.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
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

    /**
     * 단건 조회 API
     */
    @GetMapping("/{postId}")
    fun get(@PathVariable postId: Long): PostResponse {
        return postService.get(postId)
    }

    /**
     * 여러개의 글을 조회하는 API
     */
    @GetMapping
    fun get(
        @PageableDefault pageable: Pageable
    ): List<PostResponse> {
        return postService.getList(pageable)
    }

}