package com.bangkit.motix.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseData(

	@field:SerializedName("data")
	val valid: Valid
)

data class Valid(

	@field:SerializedName("Label")
	val label: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("validate")
	val validate: String
)
