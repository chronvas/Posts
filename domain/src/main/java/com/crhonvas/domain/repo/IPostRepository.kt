package com.crhonvas.domain.repo

import com.crhonvas.domain.model.posts.Post
import io.reactivex.Single

interface IPostRepository {
    fun getPost(postId: Int): Single<Post>
    fun getPosts(): Single<List<Post>>
}