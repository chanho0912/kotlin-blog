package com.kotlinblog.service

import com.kotlinblog.domain.Post
import com.kotlinblog.repository.PostRepository
import com.kotlinblog.request.PostCreate
import com.kotlinblog.util.PostSpecTransformer
import org.slf4j.LoggerFactory
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

    fun get(postId: Long): Post {
        return postRepository.findByIdOrNull(postId)
            ?: throw IllegalArgumentException("존재하지 않는 글입니다.")
    }
}