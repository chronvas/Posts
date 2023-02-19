package com.crhonvas.data.remote.model.posts;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PostsApiResponse {

    @SerializedName("userId")
    private Integer userId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    private PostsApiResponse(@NonNull Builder builder) {
        this.userId = builder.userId;
        this.id = builder.id;
        this.title = builder.title;
        this.body = builder.body;
    }

    @NonNull
    public Integer getUserId() {
        return userId == null ? -1 : userId;
    }

    @NonNull
    public Integer getId() {
        return id == null ? -1 : id;
    }

    @NonNull
    public String getTitle() {
        return title == null ? "" : title;
    }

    @NonNull
    public String getBody() {
        return body == null ? "" : body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PostsApiResponse that = (PostsApiResponse) o;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(id, that.id)
                .append(title, that.title)
                .append(body, that.body)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(id)
                .append(title)
                .append(body)
                .toHashCode();
    }

    public static final class Builder {
        private Integer userId = -1;
        private Integer id = -1;
        private String title = "";
        private String body = "";

        public Builder() {}

        @NonNull
        public Builder userId(@NonNull Integer userId) {
            this.userId = userId;
            return this;
        }

        @NonNull
        public Builder id(@NonNull Integer id) {
            this.id = id;
            return this;
        }

        @NonNull
        public Builder title(@NonNull String title) {
            this.title = title;
            return this;
        }

        @NonNull
        public Builder body(@NonNull String body) {
            this.body = body;
            return this;
        }

        public PostsApiResponse build() {
            return new PostsApiResponse(this);
        }
    }
}
