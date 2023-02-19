package com.crhonvas.domain.usecase;

import android.support.annotation.Nullable;

import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.repo.IPostRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class PostUseCase implements IUseCase<Single<Post>, Integer> {

    private final IPostRepository postRepository;

    @Inject
    public PostUseCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Single<Post> execute(@Nullable Integer postId) {
        return postRepository.getPost(postId);
    }
}
