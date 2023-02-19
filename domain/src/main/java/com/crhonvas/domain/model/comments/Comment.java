package com.crhonvas.domain.model.comments;

import androidx.annotation.NonNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Comment {

    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;

    private Comment(@NonNull Builder builder) {
        this.postId = builder.postId;
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.body = builder.body;
    }

    @NonNull
    public Integer getPostId() {
        return postId == null ? -1 : postId;
    }

    @NonNull
    public Integer getId() {
        return id == null ? -1 : id;
    }

    @NonNull
    public String getName() {
        return name == null ? "" : name;
    }

    @NonNull
    public String getEmail() {
        return email == null ? "" : email;
    }

    @NonNull
    public String getBody() {
        return body == null ? "" : body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Comment that = (Comment) o;

        return new EqualsBuilder()
                .append(postId, that.postId)
                .append(id, that.id)
                .append(name, that.name)
                .append(email, that.email)
                .append(body, that.body)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(postId)
                .append(id)
                .append(name)
                .append(email)
                .append(body)
                .toHashCode();
    }

    public static final class Builder {
        private Integer postId = -1;
        private Integer id = -1;
        private String name = "";
        private String email = "";
        private String body = "";

        public Builder() {}

        @NonNull
        public Builder postId(@NonNull Integer postId) {
            this.postId = postId;
            return this;
        }

        @NonNull
        public Builder id(@NonNull Integer id) {
            this.id = id;
            return this;
        }

        @NonNull
        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        @NonNull
        public Builder email(@NonNull String email) {
            this.email = email;
            return this;
        }

        @NonNull
        public Builder body(@NonNull String body) {
            this.body = body;
            return this;
        }

        @NonNull
        public Comment build() {
            return new Comment(this);
        }
    }
}
