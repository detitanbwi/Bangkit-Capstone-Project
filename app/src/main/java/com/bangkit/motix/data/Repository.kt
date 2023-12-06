package com.bangkit.motix.data

import com.bangkit.motix.data.remote.retrofit.ApiService

class Repository private constructor(private val apiService: ApiService) {



    companion object {

        private const val TAG = "Repository"

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