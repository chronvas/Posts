package com.crhonvas.domain;

import com.crhonvas.domain.model.comments.Comment;
import com.crhonvas.domain.model.posts.Post;
import com.crhonvas.domain.model.user.User;
import com.crhonvas.domain.repo.ICommentsRepository;
import com.crhonvas.domain.repo.IPostRepository;
import com.crhonvas.domain.repo.IUserRepository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

public class MockHelpers {


    //region Comments
    public static final int COMMENT_1_Id = 213;
    public static final Comment COMMENT_1 = new Comment.Builder()
            .body("comment body 1")
            .email("comment email 1")
            .id(COMMENT_1_Id)
            .postId(456)
            .build();
    public static final int COMMENT_2_Id = 214;
    public static final Comment COMMENT_2 = new Comment.Builder()
            .body("comment body 2")
            .email("comment email 2")
            .id(COMMENT_2_Id)
            .postId(457)
            .build();
    public static final List<Comment> COMMENT_LIST = Arrays.asList(COMMENT_1, COMMENT_2);
    //endregion

    //region Posts
    public static final int POST_1_Id = 881;
    public static final Post POST_1 = new Post.Builder().body("post body 1").id(POST_1_Id).userId(284).build();
    public static final int POST_2_Id = 881;
    public static final Post POST_2 = new Post.Builder().body("post body 1").id(POST_2_Id).userId(285).build();
    public static final List<Post> POST_LIST = Arrays.asList(POST_1, POST_2);
    //endregion

    //region User
    public static final int USER_Id = 385;
    public static final User USER = new User.Builder().userName("USER NAME").name("NAME").id(USER_Id).build();
    //endregion

    public static final ICommentsRepository mockCommentsRepository = new ICommentsRepository() {
        @Override
        public Single<Comment> getCommentById(Integer commentId) {
            return Single.just(COMMENT_1);
        }

        @Override
        public Single<List<Comment>> getComments() {
            return Single.just(Arrays.asList(COMMENT_1, COMMENT_2));
        }

        @Override
        public Single<List<Comment>> getCommentsForPostId(Integer commentId) {
            return Single.just(Arrays.asList(COMMENT_1, COMMENT_2));
        }
    };
    public static final ICommentsRepository mockErrorCommentsRepository = new ICommentsRepository() {
        @Override
        public Single<Comment> getCommentById(Integer commentId) {
            return Single.error(new Throwable("mockErrorCommentsRepository"));
        }

        @Override
        public Single<List<Comment>> getComments() {
            return Single.error(new Throwable("mockErrorCommentsRepository"));
        }

        @Override
        public Single<List<Comment>> getCommentsForPostId(Integer commentId) {
            return Single.error(new Throwable("mockErrorCommentsRepository"));
        }
    };

    public static final IPostRepository mockPostsRepository = new IPostRepository() {
        @Override
        public Single<Post> getPost(Integer postId) {
            return Single.just(POST_1);
        }

        @Override
        public Single<List<Post>> getPosts() {
            return Single.just(Arrays.asList(POST_1, POST_2));
        }
    };
    public static final IPostRepository mockErrorPostsRepository = new IPostRepository() {
        @Override
        public Single<Post> getPost(Integer postId) {
            return Single.error(new Throwable("mockErrorPostsRepository"));
        }

        @Override
        public Single<List<Post>> getPosts() {
            return Single.error(new Throwable("mockErrorPostsRepository"));
        }
    };


    public static final IUserRepository mockUserRepository= new IUserRepository() {
        @Override
        public Single<User> getUser(Integer userId) {
            return Single.just(USER);
        }
    };
    public static final IUserRepository mockErrorUserRepository = new IUserRepository() {
        @Override
        public Single<User> getUser(Integer userId) {
            return Single.error(new Throwable("mockErrorUserRepository"));
        }
    };
}
