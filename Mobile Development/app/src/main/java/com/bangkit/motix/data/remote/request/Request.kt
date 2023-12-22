package com.bangkit.motix.data.remote.request

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("text")
    val text: String
)