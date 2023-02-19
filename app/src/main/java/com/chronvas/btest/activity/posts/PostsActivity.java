package com.chronvas.btest.activity.posts;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chronvas.btest.R;
import com.chronvas.btest.activity.post.PostActivity;
import com.chronvas.btest.activity.posts.viewmodel.PostItem;
import com.chronvas.btest.activity.posts.viewmodel.PostsVieModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PostsActivity extends AppCompatActivity implements IPostsActivityContract.View, PostItemClickListener {

    @Inject
    IPostsActivityContract.Presenter presenter;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager linearLayoutManager;
    private PostsAdapter postsAdapter;
    private PostsVieModel postsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            PostsVieModel postsViewModel = savedInstanceState.getParcelable("postsViewModel");
            if (postsViewModel != null) {
                setItems(postsViewModel);
            } else {
                presenter.onStart();
            }
        } else {
            presenter.onStart();
        }

        setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.refresh());
        postsAdapter = new PostsAdapter(this);
        recyclerView.setAdapter(postsAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
    }

    private void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setItems(PostsVieModel postsViewModel) {
        this.postsViewModel = postsViewModel;
        setRefreshing(postsViewModel.isRefreshing());
        List<PostItem> postItems = postsViewModel.getPostItems();
        if (postItems != null) {
            postsAdapter.setData(postsViewModel.getPostItems());
            if (recyclerView.getLayoutManager() == null) {
                recyclerView.setLayoutManager(linearLayoutManager);
            }
            if (recyclerView.getItemDecorationCount() == 0) {
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        }
    }

    @Override
    public void postItemClicked(PostItem post) {
        presenter.postItemClicked(post);
    }

    @Override
    public void startPostActivityForItem(PostItem post) {
        Intent postActivityIntent = new Intent(this, PostActivity.class);
        postActivityIntent.putExtra("POST_ITEM", post);
        startActivity(postActivityIntent);
    }

    @Override
    public void errorMessage() {
        Toast.makeText(this, "Error loading posts list.", Toast.LENGTH_LONG).show();
        setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("postsViewModel", postsViewModel);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
