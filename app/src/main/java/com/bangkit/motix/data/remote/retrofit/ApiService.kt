package com.bangkit.motix.data.remote.retrofit

import com.bangkit.motix.data.remote.request.Request
import com.bangkit.motix.data.remote.response.ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST
    suspend fun checkDetect(
        @Body
        request: Request
    ): Response<ResponseData>

}