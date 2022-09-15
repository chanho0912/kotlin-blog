package com.kotlinblog.service

import com.kotlinblog.domain.Post
import com.kotlinblog.repository.PostRepository
import com.kotlinblog.request.PostCreate
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PostServiceTest {

    @Autowired
    lateinit var sut: PostService

    @Autowired
    lateinit var repository: PostRepository

    @Test
    fun `글 작성`() {

        val request = PostCreate(
            title = "test title",
            content = "test content",
        )

        sut.write(request)

        assertThat(repository.findAll()).hasSize(1)

        val result: Post = repository.findByTitle("test title")
        assertThat(result.content).isEqualTo("test content")
    }

    @Test
    fun `글 1개 조회`() {
        val postId = 1L

        val post = sut.get(postId)


    }
}