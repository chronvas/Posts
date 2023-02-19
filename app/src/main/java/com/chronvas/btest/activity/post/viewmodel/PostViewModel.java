package com.chronvas.btest.activity.post.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PostViewModel implements Parcelable {

    public static final Parcelable.Creator<PostViewModel> CREATOR = new Parcelable.Creator<PostViewModel>() {
        @Override
        public PostViewModel createFromParcel(Parcel source) {return new PostViewModel(source);}

        @Override
        public PostViewModel[] newArray(int size) {return new PostViewModel[size];}
    };

    private String postTitle;
    private String postBody;
    private String userName;
    private Integer commentsNumber;
    private boolean refreshing;

    public PostViewModel(String postTitle, String postBody, String userName, Integer commentsNumber, boolean refreshing) {
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.userName = userName;
        this.commentsNumber = commentsNumber;
        this.refreshing = refreshing;
    }

    protected PostViewModel(Parcel in) {
        this.postTitle = in.readString();
        this.postBody = in.readString();
        this.userName = in.readString();
        this.commentsNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.refreshing = in.readByte() != 0;
    }

    public boolean shouldSetRefreshing() {
        return refreshing;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.postTitle);
        dest.writeString(this.postBody);
        dest.writeString(this.userName);
        dest.writeValue(this.commentsNumber);
        dest.writeByte(this.refreshing ? (byte) 1 : (byte) 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PostViewModel that = (PostViewModel) o;

        return new EqualsBuilder()
                .append(refreshing, that.refreshing)
                .append(postTitle, that.postTitle)
                .append(postBody, that.postBody)
                .append(userName, that.userName)
                .append(commentsNumber, that.commentsNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(postTitle)
                .append(postBody)
                .append(userName)
                .append(commentsNumber)
                .append(refreshing)
                .toHashCode();
    }
}
