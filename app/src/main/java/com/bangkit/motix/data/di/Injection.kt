package com.bangkit.motix.data.di

import android.content.Context
import com.bangkit.motix.data.Repository
import com.bangkit.motix.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository? {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}