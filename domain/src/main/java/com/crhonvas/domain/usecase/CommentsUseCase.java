package com.crhonvas.domain.usecase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.repo.ICommentsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CommentsUseCase implements IUseCase<Single<List<Comment>>, Integer> {

    @NonNull
    private final ICommentsRepository commentsRepository;

    @Inject
    public CommentsUseCase(@NonNull ICommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Single<List<Comment>> execute(@Nullable Integer commentID) {
        return commentsRepository.getComments();
    }
}
