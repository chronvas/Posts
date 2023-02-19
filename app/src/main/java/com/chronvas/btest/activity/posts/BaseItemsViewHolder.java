package com.chronvas.btest.activity.posts;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.chronvas.btest.activity.posts.viewmodel.PostItem;

abstract class BaseItemsViewHolder extends RecyclerView.ViewHolder {
    public BaseItemsViewHolder(View view) {
        super(view);
    }

    abstract void bind(PostItem post);
}
