package com.crhonvas.data.remote.repository;

import com.crhonvas.data.mappers.posts.PostsApiToDomainMapper;
import com.crhonvas.data.remote.api.posts.IPostsApi;
import com.crhonvas.domain.IDomainSchedulerProvider;
import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.repo.IPostRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class PostsRepository implements IPostRepository {

    private final IPostsApi postsApi;
    private final PostsApiToDomainMapper postsMapper;
    private final IDomainSchedulerProvider schedulerProvider;

    @Inject
    public PostsRepository(IPostsApi postsApi, PostsApiToDomainMapper postsMapper,
                           IDomainSchedulerProvider schedulerProvider) {
        this.postsApi = postsApi;
        this.postsMapper = postsMapper;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<Post> getPost(Integer postId) {
        return postsApi.getPost(postId)
                .subscribeOn(schedulerProvider.io())
                .map(postsMapper::map)
                .subscribeOn(schedulerProvider.computation());
    }

    @Override
    public Single<List<Post>> getPosts() {
        return postsApi.getPosts()
                .subscribeOn(schedulerProvider.io())
                .map(postsMapper::map)
                .subscribeOn(schedulerProvider.computation());
    }
}
