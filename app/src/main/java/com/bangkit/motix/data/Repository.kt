package com.bangkit.motix.data

import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.liveData
import com.bangkit.motix.data.remote.retrofit.ApiService
import com.google.gson.Gson
import java.lang.Exception

class Repository private constructor(private val apiService: ApiService) {


    fun checkResult(text: String) = liveData {
        emit(Result.Loading)
        try {
            val reponse = apiService.checkDetect(text)
            emit(Result.Success(reponse))
        } catch (e: retrofit2.HttpException) {
            val jsonString = e.response()?.errorBody()?.string()
            Log.d(TAG, "StoryRepository: $jsonString")
            val errorBody = Gson().fromJson(jsonString, )
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage))
        } catch (e: Exception) {
            emit(Result.Error("Lost Connection"))
        }
    }

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