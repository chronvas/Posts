package com.chronvas.btest.repo;

import com.crhonvas.data.remote.repository.CommentsRepository;
import com.crhonvas.data.remote.repository.PostsRepository;
import com.crhonvas.data.remote.repository.UserRepository;
import com.crhonvas.domain.repo.ICommentsRepository;
import com.crhonvas.domain.repo.IPostRepository;
import com.crhonvas.domain.repo.IUserRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract IPostRepository providePostsRepository(PostsRepository postsRepository);

    @Binds
    abstract IUserRepository provideUserRepository(UserRepository userRepository);

    @Binds
    abstract ICommentsRepository provideCommentsRepository(CommentsRepository commentsRepository);
}
