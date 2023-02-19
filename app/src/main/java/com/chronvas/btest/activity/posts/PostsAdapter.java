package com.chronvas.btest.activity.posts;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chronvas.btest.R;
import com.chronvas.btest.activity.posts.viewmodel.PostItem;

import java.util.List;

import timber.log.Timber;

public class PostsAdapter extends RecyclerView.Adapter<BaseItemsViewHolder> {

    private PostItemClickListener listener;
    private List<PostItem> posts;

    public PostsAdapter(@NonNull PostItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Setter for the data to be displayed.
     *
     * @param posts The Posts list to be displayed
     */
    public void setData(@NonNull List<PostItem> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public BaseItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ContentItemViewHolder(inflater.inflate(R.layout.item_post, parent, false), parent, listener);
    }

    @Override
    public void onBindViewHolder(BaseItemsViewHolder holder, final int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        if (posts == null) return 0;
        else return posts.size();
    }

    private static class ContentItemViewHolder extends BaseItemsViewHolder {
        private final TextView postTitleTextView;
        private final ImageView userAvagarImageView;
        private final ProgressBar progressBar;
        private final View view;
        private final ViewGroup parent;
        private final PostItemClickListener listener;

        ContentItemViewHolder(View view, ViewGroup parent, PostItemClickListener listener) {
            super(view);
            this.view = view;
            this.parent = parent;
            this.listener = listener;
            this.postTitleTextView = itemView.findViewById(R.id.post_title);
            this.userAvagarImageView = itemView.findViewById(R.id.user_avatar);
            this.progressBar = itemView.findViewById(R.id.progress_bar);
        }

        @Override
        public void bind(PostItem post) {
            if (post == null) {
                return;
            }
            if (listener == null) {
                Timber.e("calling class must implement: PostItemClickListener");
                return;
            }
            view.setOnClickListener(v -> listener.postItemClicked(post));
            Glide
                    .with(parent)
                    .load("https://api.adorable.io/avatars/80/" + post.getUserId())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                    Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model,
                                                       Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            userAvagarImageView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(userAvagarImageView);

            postTitleTextView.setText(post.getPostTitle());
        }
    }
}

