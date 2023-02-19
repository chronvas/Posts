package com.crhonvas.domain.repo;

import com.crhonvas.domain.model.posts.Post;

import java.util.List;

import io.reactivex.Single;

public interface IPostRepository {
    Single<Post> getPost(Integer postId);

    Single<List<Post>> getPosts();
}
