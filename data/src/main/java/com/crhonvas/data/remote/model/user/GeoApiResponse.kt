package com.crhonvas.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class GeoApiResponse(
    @SerializedName("lat")
    val lat: String,

    @SerializedName("lng")
    val lng: String
)