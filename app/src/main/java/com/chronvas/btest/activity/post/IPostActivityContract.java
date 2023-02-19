package com.chronvas.btest.activity.post;

import android.support.annotation.Nullable;

import com.chronvas.btest.activity.post.viewmodel.PostViewModel;
import com.chronvas.btest.activity.posts.viewmodel.PostItem;

interface IPostActivityContract {
    interface View {
        void setItem(PostViewModel items);

        void errorMessage();

        void userNameErrorMessage();

        void commentsSizeError();
    }

    interface Presenter {
        void onDestroy();

        void onStart(PostItem postItem);

        void onRefresh(PostViewModel postViewModel);
    }
}
