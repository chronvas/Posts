package com.crhonvas.domain.model.posts;

import androidx.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Post {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    private Post(@NonNull Builder builder) {
        this.userId = builder.userId;
        this.id = builder.id;
        this.title = builder.title;
        this.body = builder.body;
    }

    public Integer getUserId() {
        return userId == null ? -1 : userId;
    }

    public Integer getId() {
        return id == null ? -1 : id;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public String getBody() {
        return body == null ? "" : body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return new EqualsBuilder()
                .append(userId, post.userId)
                .append(id, post.id)
                .append(title, post.title)
                .append(body, post.body)
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

        public Post build() {
            return new Post(this);
        }
    }
}
