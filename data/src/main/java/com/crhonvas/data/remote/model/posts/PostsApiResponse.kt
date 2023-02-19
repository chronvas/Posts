package com.crhonvas.data.remote.model.posts

import com.google.gson.annotations.SerializedName

data class PostsApiResponse(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String,
)