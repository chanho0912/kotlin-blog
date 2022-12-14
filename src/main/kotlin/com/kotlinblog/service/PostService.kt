package com.kotlinblog.service

import com.kotlinblog.constaant.ValidationConstants
import com.kotlinblog.repository.PostRepository
import com.kotlinblog.request.PostCreate
import com.kotlinblog.response.PostResponse
import com.kotlinblog.util.PostSpecTransformer
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun write(request: PostCreate) {
        postRepository.save(
            PostSpecTransformer.transformToEntity(request)
        )
    }

    fun get(postId: Long): PostResponse {
        return postRepository.findByIdOrNull(postId)?.let {
            PostSpecTransformer.transformToPostResponse(it)
        } ?: throw IllegalArgumentException(ValidationConstants.POST_NOT_FOUND)
    }

    fun getList(pageable: Pageable): List<PostResponse> {
        return postRepository.findAll(pageable).map {
            PostSpecTransformer.transformToPostResponse(it)
        }.toList()
    }
}