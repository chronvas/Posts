package com.crhonvas.domain.repo

import com.crhonvas.domain.model.comments.Comment
import io.reactivex.Single

interface ICommentsRepository {
    fun getCommentById(commentId: Integer): Single<Comment>
    fun getComments(): Single<List<Comment>>
    fun getCommentsForPostId(postId: Integer): Single<List<Comment>>
}