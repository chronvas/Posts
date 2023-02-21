package com.crhonvas.domain.usecase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.repo.IPostRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class PostsUseCase implements IUseCase<Single<List<Post>>, Integer> {

    @NonNull
    private final IPostRepository postRepository;

    @Inject
    public PostsUseCase(@NonNull IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Single<List<Post>> execute(@Nullable Integer v) {
        return postRepository.getPosts();
    }
}
