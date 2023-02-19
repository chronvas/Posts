package com.chronvas.btest.activity.posts.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class PostsVieModel implements Parcelable {
    private boolean refreshing;
    private List<PostItem> postItems;

    public PostsVieModel(List<PostItem> postItems, boolean refreshing) {
        this.postItems = postItems;
        this.refreshing = refreshing;
    }

    public List<PostItem> getPostItems() {
        return postItems;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.refreshing ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.postItems);
    }

    protected PostsVieModel(Parcel in) {
        this.refreshing = in.readByte() != 0;
        this.postItems = in.createTypedArrayList(PostItem.CREATOR);
    }

    public static final Parcelable.Creator<PostsVieModel> CREATOR = new Parcelable.Creator<PostsVieModel>() {
        @Override
        public PostsVieModel createFromParcel(Parcel source) {return new PostsVieModel(source);}

        @Override
        public PostsVieModel[] newArray(int size) {return new PostsVieModel[size];}
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PostsVieModel that = (PostsVieModel) o;

        return new EqualsBuilder()
                .append(refreshing, that.refreshing)
                .append(postItems, that.postItems)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(refreshing)
                .append(postItems)
                .toHashCode();
    }
}
