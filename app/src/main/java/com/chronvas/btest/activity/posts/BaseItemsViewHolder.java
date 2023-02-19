package com.chronvas.btest.activity.posts;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.crhonvas.domain.model.posts.Post;

abstract class BaseItemsViewHolder extends RecyclerView.ViewHolder {
    public BaseItemsViewHolder(View view) {
        super(view);
    }

    abstract void bind(PostItem post);
}
