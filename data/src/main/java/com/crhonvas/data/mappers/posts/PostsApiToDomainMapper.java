package com.crhonvas.data.mappers.posts;


import android.support.annotation.NonNull;

import com.crhonvas.data.remote.model.posts.PostsApiResponse;
import com.crhonvas.data.utils.ListUtils;
import com.crhonvas.domain.model.posts.Post;

import java.util.List;

import javax.inject.Inject;

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread

/**
 * Mapper from {@link PostsApiResponse} to {@link Post}
 */
public class PostsApiToDomainMapper {

    @Inject
    public PostsApiToDomainMapper() {
    }

    @NonNull
    public Post map(@NonNull PostsApiResponse postsApiResponse) {
        if (postsApiResponse == null) {
            return new Post.Builder().build();
        }
        return new Post.Builder()
                .userId(postsApiResponse.getUserId())
                .id(postsApiResponse.getId())
                .title(postsApiResponse.getTitle())
                .body(postsApiResponse.getBody())
                .build();
    }

    @NonNull
    public List<Post> map(@NonNull List<PostsApiResponse> postsApiResponse) {
        return ListUtils.map(postsApiResponse, this::map);
    }
}
