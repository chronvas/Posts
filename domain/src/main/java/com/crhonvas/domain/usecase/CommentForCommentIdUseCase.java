package com.crhonvas.domain.usecase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.repo.ICommentsRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class CommentForCommentIdUseCase implements IUseCase<Single<Comment>, Integer> {

    @NonNull
    private final ICommentsRepository commentsRepository;

    @Inject
    public CommentForCommentIdUseCase(@NonNull ICommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Single<Comment> execute(@Nullable Integer commentID) {
        return commentsRepository.getCommentById(commentID);
    }
}
