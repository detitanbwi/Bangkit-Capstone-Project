package com.bangkit.motix.data

import com.bangkit.motix.data.remote.request.Request
import com.bangkit.motix.data.remote.response.ResponseData
import com.bangkit.motix.data.remote.retrofit.ApiService

class Repository private constructor(private val apiService: ApiService) {


//    fun checkResult(text: String) = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.checkDetect(text)
//            emit(Result.Success(response))
//        } catch (e: retrofit2.HttpException) {
//            val jsonString = e.response()?.errorBody()?.string()
//            Log.d(TAG, "Repository : $jsonString")
//            val errorBody = Gson().fromJson(jsonString, )
//            val errorMessage = errorBody.message
//            emit(Result.Error(errorMessage))
//        } catch (e: Exception) {
//            emit(Result.Error("Lost Connection"))
//        }
//    }
    suspend fun checkDetect(requestData: Request): ResponseData {
        return apiService.checkDetect(requestData)
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