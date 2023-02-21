package com.crhonvas.domain.model.comments

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class Comment private constructor(builder: Builder) {
    private val postId: Int?
    private val id: Int?
    private val name: String?
    private val email: String?
    private val body: String?

    init {
        postId = builder.postId
        id = builder.id
        name = builder.name
        email = builder.email
        body = builder.body
    }

    fun getPostId(): Int {
        return postId ?: -1
    }

    fun getId(): Int {
        return id ?: -1
    }

    fun getName(): String {
        return name ?: ""
    }

    fun getEmail(): String {
        return email ?: ""
    }

    fun getBody(): String {
        return body ?: ""
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as Comment
        return EqualsBuilder()
            .append(postId, that.postId)
            .append(id, that.id)
            .append(name, that.name)
            .append(email, that.email)
            .append(body, that.body)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(postId)
            .append(id)
            .append(name)
            .append(email)
            .append(body)
            .toHashCode()
    }

    class Builder {
        var postId = -1
        var id = -1
        var name = ""
        var email = ""
        var body = ""
        fun postId(postId: Int): Builder {
            this.postId = postId
            return this
        }

        fun id(id: Int): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun email(email: String): Builder {
            this.email = email
            return this
        }

        fun body(body: String): Builder {
            this.body = body
            return this
        }

        fun build(): Comment {
            return Comment(this)
        }
    }
}