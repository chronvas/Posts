package com.crhonvas.data.remote.api.posts

import com.crhonvas.data.remote.model.posts.PostsApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IPostsApi {
    @get:GET("posts")
    val posts: Single<List<PostsApiResponse>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") postId: Int?): Single<PostsApiResponse>
}