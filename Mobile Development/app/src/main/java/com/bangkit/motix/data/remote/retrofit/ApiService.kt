package com.bangkit.motix.data.remote.retrofit

import com.bangkit.motix.data.remote.request.Request
import com.bangkit.motix.data.remote.response.ResponseData
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/validate")
    suspend fun checkDetect(
        @Body
        request: Request
    ): ResponseData
//    {
//        Log.d("ApiService", request.toString())
//
//        return ResponseData(
//            valid = Valid(
//                label = 0,
//                message = "ok",
//                text = "ok ini text",
//                validate = "0"
//            )
//        )
//    }

}