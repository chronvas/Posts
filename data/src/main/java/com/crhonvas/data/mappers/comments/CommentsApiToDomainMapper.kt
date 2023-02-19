package com.crhonvas.data.mappers.comments

import com.crhonvas.data.remote.model.comments.CommentApiResponse
import com.crhonvas.domain.model.comments.Comment
import javax.inject.Inject

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread
/**
 * Mapper from [CommentApiResponse] to [Comment]
 */
class CommentsApiToDomainMapper @Inject constructor() {
    fun map(commentApiResponse: CommentApiResponse): Comment {
        return if (commentApiResponse == null) {
            Comment.Builder().build()
        } else Comment.Builder()
            .postId(commentApiResponse.postId)
            .id(commentApiResponse.id)
            .name(commentApiResponse.name)
            .email(commentApiResponse.email)
            .body(commentApiResponse.body)
            .build()
    }

    fun map(commentsApiResponse: List<CommentApiResponse>): List<Comment> {
        return commentsApiResponse.map { map(it) }
    }
}