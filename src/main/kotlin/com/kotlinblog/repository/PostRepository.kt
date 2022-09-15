package com.kotlinblog.repository

import com.kotlinblog.domain.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByTitle(title: String): Post
}