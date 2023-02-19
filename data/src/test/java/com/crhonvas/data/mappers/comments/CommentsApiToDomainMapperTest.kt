package com.crhonvas.data.mappers.comments

import com.crhonvas.data.remote.model.comments.CommentApiResponse
import com.crhonvas.domain.model.comments.Comment
import java.util.Arrays
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Tests for [CommentsApiToDomainMapper]
 */
class CommentsApiToDomainMapperTest {
    private var mapper: CommentsApiToDomainMapper? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {
        mapper = CommentsApiToDomainMapper()
    }

    //region map Comment
    @Test
    @Throws(Exception::class)
    fun mapComment() {
        val actual = mapper!!.map(COMMENT_API_RESPONSE_1)
        compare(COMMENT_API_RESPONSE_1, actual)
    }

    @Test
    @Throws(Exception::class)
    fun mapComment_emptyFields() {
        val expected = CommentApiResponse.Builder().build()
        val actual = mapper!!.map(expected)
        compare(expected, actual)
    }

    @Test
    @Throws(Exception::class)
    fun mapComment_null_returnEmptyComment() {
        val input: CommentApiResponse? = null
        val expected = Comment.Builder().build()
        val actual = mapper!!.map(input!!)
        Assert.assertTrue(expected == actual)
    }

    //endregion
    //region map Comments
    @Test
    @Throws(Exception::class)
    fun mapComments() {
        val actual = mapper!!.map(COMMENTS_API_RESPONSE)
        Assert.assertTrue(actual.size == COMMENTS_API_RESPONSE.size)
        for (i in actual.indices) {
            Assert.assertTrue(compare(COMMENTS_API_RESPONSE[i], actual[i]))
        }
    }

    @Test
    @Throws(Exception::class)
    fun mapComments_emptyFields() {
        val input = Arrays.asList(CommentApiResponse.Builder().build(), CommentApiResponse.Builder().build())
        val expected = Arrays.asList(Comment.Builder().build(), Comment.Builder().build())
        val actual = mapper!!.map(input)
        Assert.assertTrue(expected == actual)
    }

    @Test
    @Throws(Exception::class)
    fun mapComments_null_returnListOfEmptyComments() {
        val input = Arrays.asList<CommentApiResponse?>(null, null)
        val expected = Arrays.asList(Comment.Builder().build(), Comment.Builder().build())
        val actual = mapper!!.map(input)
        Assert.assertTrue(expected == actual)
    } //endregion

    companion object {
        private val COMMENT_API_RESPONSE_1 = CommentApiResponse.Builder().id(11).build()
        private val COMMENT_API_RESPONSE_2 = CommentApiResponse.Builder().id(12).build()
        private val COMMENTS_API_RESPONSE = Arrays.asList(COMMENT_API_RESPONSE_1, COMMENT_API_RESPONSE_2)
        private fun compare(commentApiResponse: CommentApiResponse?, comment: Comment?): Boolean {
            return commentApiResponse!!.getBody() == comment!!.body && commentApiResponse.getEmail() == comment.email &&
                    commentApiResponse.getId().equals(comment.id) && commentApiResponse.getName() == comment.name &&
                    commentApiResponse.getPostId().equals(comment.postId)
        }
    }
}