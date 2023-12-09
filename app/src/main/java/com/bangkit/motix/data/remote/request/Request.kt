package com.bangkit.motix.data.remote.request

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("link")
    val link: String
)