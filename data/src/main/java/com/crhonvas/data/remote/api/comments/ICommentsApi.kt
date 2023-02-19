package com.crhonvas.data.remote.api.comments

import com.crhonvas.data.remote.model.comments.CommentApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ICommentsApi {
    @get:GET("comments")
    val comments: Single<List<CommentApiResponse>>

    @GET("comments/{commentId}")
    fun getCommentForCommentId(@Path("commentId") commentId: Int?): Single<CommentApiResponse>

    @GET("comments")
    fun getCommentsForPostId(@Query("postId") postId: Int?): Single<List<CommentApiResponse>>
}