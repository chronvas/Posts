package com.crhonvas.data.remote.repository;

import com.crhonvas.data.mappers.comments.CommentsApiToDomainMapper;
import com.crhonvas.data.remote.api.comments.ICommentsApi;
import com.crhonvas.domain.IDomainSchedulerProvider;
import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.repo.ICommentsRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CommentsRepository implements ICommentsRepository {

    private final ICommentsApi commentsApi;
    private final CommentsApiToDomainMapper commentsMapper;
    private final IDomainSchedulerProvider schedulerProvider;

    @Inject
    public CommentsRepository(ICommentsApi commentsApi, CommentsApiToDomainMapper commentsMapper,
                              IDomainSchedulerProvider schedulerProvider) {
        this.commentsApi = commentsApi;
        this.commentsMapper = commentsMapper;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<Comment> getCommentById(final Integer commentId) {
        return commentsApi.getCommentForCommentId(commentId)
                .subscribeOn(schedulerProvider.io())
                .map(commentsMapper::map)
                .subscribeOn(schedulerProvider.computation());
    }

    @Override
    public Single<List<Comment>> getComments() {
        return commentsApi.getComments()
                .subscribeOn(schedulerProvider.io())
                .map(commentsMapper::map)
                .subscribeOn(schedulerProvider.computation());
    }

    @Override
    public Single<List<Comment>> getCommentsForPostId(final Integer postId) {
        return commentsApi.getCommentsForPostId(postId)
                .subscribeOn(schedulerProvider.io())
                .map(commentsMapper::map)
                .subscribeOn(schedulerProvider.computation());
    }
}
