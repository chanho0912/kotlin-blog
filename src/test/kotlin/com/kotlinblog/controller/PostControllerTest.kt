package com.kotlinblog.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlinblog.constaant.RestConstants
import com.kotlinblog.constaant.ValidationConstants
import com.kotlinblog.domain.Post
import com.kotlinblog.repository.PostRepository
import com.kotlinblog.request.PostCreate
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class PostControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var repository: PostRepository

    @BeforeEach
    fun clean() {
        repository.deleteAll()
    }

    @Test
    fun `posts 요청 시 Hello world~!를 출력한다`() {

        val expect = "Hello world~!"

        mockMvc.post("/posts") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(
                PostCreate(
                    title = "글 제목 입니다.",
                    content = "글 내용 입니다. 하하하"
                )
            )
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun `posts 요청 시 title 값은 필수다`() {

        mockMvc.post("/posts") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(
                PostCreate(
                    title = "",
                    content = "글 내용 입니다. 하하하",
                )
            )
        }.andExpect {
            status { isBadRequest() }
            jsonPath("$.code") { value(RestConstants.BAD_REQUEST_CODE) }
            jsonPath("$.message") { value(RestConstants.DEFAULT_ERROR_MESSAGE) }
            jsonPath("$.validation.title") { value(ValidationConstants.NOT_BLANK_TITLE) }
        }.andDo { print() }
    }

    @Test
    fun `posts 요청 시 값이 저장된다`() {

        val expect = "Hello world~!"

        mockMvc.post("/posts") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(
                PostCreate(
                    title = "글 제목 입니다.",
                    content = "글 내용 입니다. 하하하"
                )
            )
        }.andExpect {
            status { isOk() }
        }

        assertThat(repository.findAll()).hasSize(1)

        val post: Post = repository.findByTitle("글 제목 입니다.")
        Assertions.assertNotNull(post)
        assertThat(post.content).isEqualTo("글 내용 입니다. 하하하")
    }
}