package com.crhonvas.data.mappers.posts

import com.crhonvas.data.remote.model.posts.PostsApiResponse
import com.crhonvas.domain.model.posts.Post
import javax.inject.Inject

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread
/**
 * Mapper from [PostsApiResponse] to [Post]
 */
class PostsApiToDomainMapper @Inject constructor() {
    fun map(postsApiResponse: PostsApiResponse): Post {
        return if (postsApiResponse == null) {
            Post.Builder().build()
        } else Post.Builder()
            .userId(postsApiResponse.userId)
            .id(postsApiResponse.id)
            .title(postsApiResponse.title)
            .body(postsApiResponse.body)
            .build()
    }

    fun map(postsApiResponse: List<PostsApiResponse>): List<Post> {
        return postsApiResponse.map { map(it) }
    }
}