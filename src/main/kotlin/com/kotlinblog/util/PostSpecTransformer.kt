package com.kotlinblog.util

import com.kotlinblog.domain.Post
import com.kotlinblog.request.PostCreate

class PostSpecTransformer {

    companion object {
        fun transformToEntity(request: PostCreate): Post {
            return Post(
                title = request.title,
                content = request.content,
            )
        }
    }
}