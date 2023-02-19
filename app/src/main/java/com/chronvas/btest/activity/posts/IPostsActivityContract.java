package com.chronvas.btest.activity.posts;

import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.chronvas.btest.activity.posts.viewmodel.PostsVieModel;

interface IPostsActivityContract {
    interface View {
        void setItems(PostsVieModel items);

        void startPostActivityForItem(PostItem post);

        void errorMessage();
    }

    interface Presenter {
        void onDestroy();

        void onStart();

        void postItemClicked(PostItem post);

        void refresh();
    }
}
