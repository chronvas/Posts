package com.crhonvas.data.mappers.comments;

import androidx.annotation.NonNull;

import com.crhonvas.data.remote.model.comments.CommentApiResponse;
import com.crhonvas.data.utils.ListUtils;
import com.crhonvas.domain.model.comments.Comment;

import java.util.List;

import javax.inject.Inject;

//TODO: Inject scheduler provider inorder to do mappings in a specified background thread

/**
 * Mapper from {@link CommentApiResponse} to {@link Comment}
 */
public class CommentsApiToDomainMapper {

    @Inject
    public CommentsApiToDomainMapper() {
    }

    @NonNull
    public Comment map(@NonNull CommentApiResponse commentApiResponse) {
        if (commentApiResponse == null) {
            return new Comment.Builder().build();
        }
        return new Comment.Builder()
                .postId(commentApiResponse.getPostId())
                .id(commentApiResponse.getId())
                .name(commentApiResponse.getName())
                .email(commentApiResponse.getEmail())
                .body(commentApiResponse.getBody())
                .build();
    }

    @NonNull
    public List<Comment> map(@NonNull List<CommentApiResponse> commentsApiResponse) {
        return ListUtils.map(commentsApiResponse, this::map);
    }
}
