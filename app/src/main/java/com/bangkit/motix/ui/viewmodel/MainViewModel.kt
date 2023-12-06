package com.bangkit.motix.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.bangkit.motix.data.Repository

class MainViewModel(private val repository: Repository): ViewModel(){

    fun checkResult(input: String) = repository.checkResult(input)

}