package com.crhonvas.data.remote.api.posts;

import com.crhonvas.data.remote.model.comments.CommentApiResponse;
import com.crhonvas.data.remote.model.posts.PostsApiResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPostsApi {
    @GET("posts")
    Single<List<PostsApiResponse>> getPosts();

    @GET("posts/{postId}")
    Single<PostsApiResponse> getPost(@Path("postId") Integer postId);
}
