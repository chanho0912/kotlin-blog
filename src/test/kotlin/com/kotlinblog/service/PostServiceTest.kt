package com.kotlinblog.service

import com.kotlinblog.domain.Post
import com.kotlinblog.repository.PostRepository
import com.kotlinblog.request.PostCreate
import com.kotlinblog.response.PostResponse
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

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

    @Test
    fun `글 여러개 조회`() {
        (1..30).map {
            PostCreate(
                title = "찬호 테스트 $it",
                content = "포르쉐 파나메라 $it",
            )
        }.forEach { sut.write(it) }

        val pageable: Pageable = PageRequest.of(
            0,
            5,
            Sort.by(Sort.Direction.DESC, "id")
        )


        val posts: List<PostResponse> = sut.getList(pageable = pageable) // 0번부터 5개 조회

        assertThat(posts).hasSize(5)
    }
}