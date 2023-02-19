package com.crhonvas.data.remote.model.comments

import com.google.gson.annotations.SerializedName

data class CommentApiResponse(
    @SerializedName("postId")
    val postId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("body")
    val body: String
)