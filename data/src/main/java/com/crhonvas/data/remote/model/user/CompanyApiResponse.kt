package com.crhonvas.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class CompanyApiResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("catchPhrase")
    val catchPhrase: String,

    @SerializedName("bs")
    val bs: String
)