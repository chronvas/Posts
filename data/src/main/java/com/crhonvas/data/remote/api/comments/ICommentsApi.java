package com.crhonvas.data.remote.api.comments;

import com.crhonvas.data.remote.model.comments.CommentApiResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICommentsApi {
    @GET("comments")
    Single<List<CommentApiResponse>> getComments();

    @GET("comments/{commentId}")
    Single<CommentApiResponse> getCommentForCommentId(@Path("commentId") Integer commentId);

    @GET("comments")
    Single<List<CommentApiResponse>> getCommentsForPostId(@Query("postId") Integer postId);
}
