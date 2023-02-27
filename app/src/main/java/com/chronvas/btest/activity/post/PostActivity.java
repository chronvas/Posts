package com.chronvas.btest.activity.post;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chronvas.btest.R;
import com.chronvas.btest.activity.post.viewmodel.PostViewModel;
import com.chronvas.btest.activity.posts.viewmodel.PostItem;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PostActivity extends AppCompatActivity implements IPostActivityContract.View {


    private IPostActivityContract.Presenter presenter;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.post_title)
    TextView postTitle;
    @BindView(R.id.post_body)
    TextView postBody;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.comments_number)
    TextView commentsNumber;
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ViewModelProvider(this).get(PostActivityPresenter.class);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        setRefreshing(true);

        if (savedInstanceState != null && savedInstanceState.getParcelable("postViewModel") != null) {
            setItem(savedInstanceState.getParcelable("postViewModel"));
        }

        if (getIntent().getExtras().getParcelable("POST_ITEM") != null) {
            PostItem postItem = getIntent().getExtras().getParcelable("POST_ITEM");
            presenter.onStart(postItem);
        } else {
            finish();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh(postViewModel);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("postViewModel", postViewModel);
    }

    private void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setItem(PostViewModel postViewModel) {
        this.postViewModel = postViewModel;
        final String postTitle = postViewModel.getPostTitle();
        final String postBody = postViewModel.getPostBody();
        final String userName = postViewModel.getUserName();
        final Integer commentsNumber = postViewModel.getCommentsNumber();
        final boolean refreshing = postViewModel.shouldSetRefreshing();

        setRefreshing(refreshing);

        if (StringUtils.isNotBlank(postTitle)) {
            this.postTitle.setText(postTitle);
        }
        if (StringUtils.isNotBlank(postBody)) {
            this.postBody.setText(postBody);
        }
        if (StringUtils.isNotBlank(userName)) {
            this.userName.setText(userName);
        }
        if (commentsNumber != null) {
            this.commentsNumber.setText(String.valueOf(commentsNumber));
        }
    }

    @Override
    public void errorMessage() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        setRefreshing(false);
    }

    @Override
    public void userNameErrorMessage() {
        Toast.makeText(this, "Username for this post could not be retrieved.", Toast.LENGTH_LONG).show();
        setRefreshing(false);
    }

    @Override
    public void commentsSizeError() {
        Toast.makeText(this, "Comments for this post could not be retrieved.", Toast.LENGTH_LONG).show();
        setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
