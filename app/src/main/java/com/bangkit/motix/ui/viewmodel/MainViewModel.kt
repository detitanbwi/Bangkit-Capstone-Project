package com.bangkit.motix.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.motix.data.Repository
import com.bangkit.motix.data.remote.request.Request
import com.bangkit.motix.data.remote.response.ResponseData
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel(){

    private val _response = MutableLiveData<ResponseData>()
    val response: LiveData<ResponseData> get() = _response

    //suspend fun checkDetect(input: String) = repository.checkDetect(Request(input))

    fun checkDetect(input: String) {
        viewModelScope.launch {
            try {
                Log.d("SampaiVM",Request(input).toString())
                val result = repository.checkDetect(input)
                _response.value = result
            } catch (e: Exception) {
                // Handle error, jika diperlukan
                Log.d("checkDetect", e.toString())
            }
        }
    }
}