package com.chronvas.btest.activity.posts.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PostItem implements Parcelable {

    public static final Parcelable.Creator<PostItem> CREATOR = new Parcelable.Creator<PostItem>() {
        @Override
        public PostItem createFromParcel(Parcel source) {return new PostItem(source);}

        @Override
        public PostItem[] newArray(int size) {return new PostItem[size];}
    };

    private String body;
    private String postTitle;
    private Integer userId;
    private Integer postId;

    public PostItem(Integer userId, Integer postId, String postTitle, String body) {
        this.userId = userId;
        this.postId = postId;
        this.postTitle = postTitle;
        this.body = body;
    }

    protected PostItem(Parcel in) {
        this.body = in.readString();
        this.postTitle = in.readString();
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.postId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public String getPostTitle() {
        return postTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeString(this.postTitle);
        dest.writeValue(this.userId);
        dest.writeValue(this.postId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PostItem postItem = (PostItem) o;

        return new EqualsBuilder()
                .append(body, postItem.body)
                .append(postTitle, postItem.postTitle)
                .append(userId, postItem.userId)
                .append(postId, postItem.postId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(body)
                .append(postTitle)
                .append(userId)
                .append(postId)
                .toHashCode();
    }
}
