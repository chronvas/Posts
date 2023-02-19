package com.crhonvas.data.mappers.posts

import com.crhonvas.data.remote.model.posts.PostsApiResponse
import com.crhonvas.domain.model.posts.Post
import java.util.Arrays
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Tests for [PostsApiToDomainMapper]
 */
class PostsApiToDomainMapperTest {
    private var mapper: PostsApiToDomainMapper? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        mapper = PostsApiToDomainMapper()
    }

    //region map Post
    @Test
    @Throws(Exception::class)
    fun mapPost() {
        val actual = mapper!!.map(POST_API_RESPONSE_1)
        compare(POST_API_RESPONSE_1, actual)
    }

    @Test
    @Throws(Exception::class)
    fun mapPost_emptyFields() {
        val expected = PostsApiResponse.Builder().build()
        val actual = mapper!!.map(expected)
        compare(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun mapPost_null_returnEmptyPost() {
        val input: PostsApiResponse? = null
        val expected = Post.Builder().build()
        val actual = mapper!!.map(input!!)
        Assert.assertTrue(expected == actual)
    }

    //endregion
    //region map Posts
    @Test
    @Throws(Exception::class)
    fun mapPosts() {
        val actual = mapper!!.map(POSTS_API_RESPONSE)
        Assert.assertTrue(actual.size == POSTS_API_RESPONSE.size)
        for (i in actual.indices) {
            Assert.assertTrue(compare(POSTS_API_RESPONSE[i], actual[i]))
        }
    }

    @Test
    @Throws(Exception::class)
    fun mapPosts_emptyFields() {
        val input = Arrays.asList(PostsApiResponse.Builder().build(), PostsApiResponse.Builder().build())
        val expected = Arrays.asList(Post.Builder().build(), Post.Builder().build())
        val actual = mapper!!.map(input)
        Assert.assertTrue(expected == actual)
    }

    @Test
    @Throws(Exception::class)
    fun mapPosts_null_returnListOfEmptyPosts() {
        val input = Arrays.asList<PostsApiResponse?>(null, null)
        val expected = Arrays.asList(Post.Builder().build(), Post.Builder().build())
        val actual = mapper!!.map(input)
        Assert.assertTrue(expected == actual)
    } //endregion

    companion object {
        private val POST_API_RESPONSE_1 = PostsApiResponse.Builder().id(22).build()
        private val POST_API_RESPONSE_2 = PostsApiResponse.Builder().id(23).build()
        private val POSTS_API_RESPONSE = Arrays.asList(POST_API_RESPONSE_1, POST_API_RESPONSE_2)
        private fun compare(postsApiResponse: PostsApiResponse?, post: Post?): Boolean {
            return postsApiResponse!!.getUserId().equals(post!!.userId) &&
                    postsApiResponse.getId()
                        .equals(post.id) && postsApiResponse.getTitle() == post.title && postsApiResponse.getBody() == post.body
        }
    }
}