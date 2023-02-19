package com.crhonvas.data.remote.repository

import com.crhonvas.data.mappers.comments.CommentsApiToDomainMapper
import com.crhonvas.data.remote.api.comments.ICommentsApi
import com.crhonvas.data.remote.model.comments.CommentApiResponse
import com.crhonvas.domain.IDomainSchedulerProvider
import com.crhonvas.domain.model.comments.Comment
import com.crhonvas.domain.repo.ICommentsRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsRepository @Inject constructor(
    private val commentsApi: ICommentsApi, private val commentsMapper: CommentsApiToDomainMapper,
    private val schedulerProvider: IDomainSchedulerProvider
) : ICommentsRepository {
    override fun getCommentById(commentId: Int): Single<Comment> {
        return commentsApi.getCommentForCommentId(commentId)
            .subscribeOn(schedulerProvider.io())
            .map { commentApiResponse: CommentApiResponse -> commentsMapper.map(commentApiResponse) }
            .subscribeOn(schedulerProvider.computation())
    }

    override fun getComments(): Single<List<Comment>> {
        return commentsApi.comments
            .subscribeOn(schedulerProvider.io())
            .map { commentsMapper.map(it) }
            .subscribeOn(schedulerProvider.computation())
    }

    override fun getCommentsForPostId(postId: Int): Single<List<Comment>> {
        return commentsApi.getCommentsForPostId(postId)
            .subscribeOn(schedulerProvider.io())
            .map { commentsMapper.map(it) }
            .subscribeOn(schedulerProvider.computation())
    }
}