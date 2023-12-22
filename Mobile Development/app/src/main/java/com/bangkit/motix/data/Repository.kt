package com.bangkit.motix.data

import android.util.Log
import com.bangkit.motix.data.remote.request.Request
import com.bangkit.motix.data.remote.response.ResponseData
import com.bangkit.motix.data.remote.retrofit.ApiService

class Repository private constructor(private val apiService: ApiService) {


    suspend fun checkDetect(text: String): ResponseData {
        Log.d("SampaiRepo",text)
        return apiService.checkDetect(Request(text))
    }

    companion object {

        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository? {
            synchronized(this) {
                instance = Repository(apiService)
            }
            return instance
        }
    }
}