package com.crhonvas.domain.repo;


import com.crhonvas.domain.model.comments.Comment;

import java.util.List;

import io.reactivex.Single;


public interface ICommentsRepository {
    Single<Comment> getCommentById(Integer commentId);

    Single<List<Comment>> getComments();

    Single<List<Comment>> getCommentsForPostId(Integer commentId);
}
