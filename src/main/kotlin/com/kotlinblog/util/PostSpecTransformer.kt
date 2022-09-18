package com.kotlinblog.util

import com.kotlinblog.domain.Post
import com.kotlinblog.request.PostCreate
import com.kotlinblog.response.PostResponse

class PostSpecTransformer {

    companion object {
        fun transformToEntity(request: PostCreate): Post {
            return Post(
                title = request.title,
                content = request.content,
            )
        }

        fun transformToPostResponse(request: Post): PostResponse {
            return PostResponse(
                id = request.id,
                title = request.title,
                content = request.content
            )
        }
    }
}