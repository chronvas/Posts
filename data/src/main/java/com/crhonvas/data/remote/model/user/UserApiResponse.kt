package com.crhonvas.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class UserApiResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("address")
    val address: AddressApiResponse,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("website")
    val website: String,

    @SerializedName("company")
    val userCompanyApiResponse: CompanyApiResponse
)