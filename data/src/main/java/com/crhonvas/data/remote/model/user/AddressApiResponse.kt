package com.crhonvas.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class AddressApiResponse(
    @SerializedName("street")
    var street: String,

    @SerializedName("suite")
    var suite: String,

    @SerializedName("city")
    var city: String,

    @SerializedName("zipCode")
    var zipCode: String,

    @SerializedName("geo")
    var geo: GeoApiResponse
)