package com.crhonvas.data.remote.repository

import com.crhonvas.data.mappers.posts.PostsApiToDomainMapper
import com.crhonvas.data.remote.api.posts.IPostsApi
import com.crhonvas.data.remote.model.posts.PostsApiResponse
import com.crhonvas.domain.IDomainSchedulerProvider
import com.crhonvas.domain.model.posts.Post
import com.crhonvas.domain.repo.IPostRepository
import io.reactivex.Single
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsApi: IPostsApi, private val postsMapper: PostsApiToDomainMapper,
    private val schedulerProvider: IDomainSchedulerProvider
) : IPostRepository {
    override fun getPost(postId: Int): Single<Post> {
        return postsApi.getPost(postId)
            .subscribeOn(schedulerProvider.io())
            .map { postsApiResponse: PostsApiResponse -> postsMapper.map(postsApiResponse) }
            .subscribeOn(schedulerProvider.computation())
    }

    override fun getPosts(): Single<List<Post>> {
        return postsApi.posts
            .subscribeOn(schedulerProvider.io())
            .map { postsMapper.map(it) }
            .subscribeOn(schedulerProvider.computation())
    }
}