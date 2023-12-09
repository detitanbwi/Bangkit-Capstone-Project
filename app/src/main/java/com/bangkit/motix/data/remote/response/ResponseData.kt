package com.bangkit.motix.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseData(

	@field:SerializedName("valid")
	val valid: Valid
)

data class Valid(

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("validate")
	val validate: String
)
