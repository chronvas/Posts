package com.crhonvas.domain.model.posts

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class Post private constructor(builder: Builder) {
    private val userId: Int?
    private val id: Int?
    private val title: String?
    private val body: String?

    init {
        userId = builder.userId
        id = builder.id
        title = builder.title
        body = builder.body
    }

    fun getUserId(): Int {
        return userId ?: -1
    }

    fun getId(): Int {
        return id ?: -1
    }

    fun getTitle(): String {
        return title ?: ""
    }

    fun getBody(): String {
        return body ?: ""
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val post = o as Post
        return EqualsBuilder()
            .append(userId, post.userId)
            .append(id, post.id)
            .append(title, post.title)
            .append(body, post.body)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(userId)
            .append(id)
            .append(title)
            .append(body)
            .toHashCode()
    }

    class Builder {
        var userId = -1
        var id = -1
        var title = ""
        var body = ""
        fun userId(userId: Int): Builder {
            this.userId = userId
            return this
        }

        fun id(id: Int): Builder {
            this.id = id
            return this
        }

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun body(body: String): Builder {
            this.body = body
            return this
        }

        fun build(): Post {
            return Post(this)
        }
    }
}