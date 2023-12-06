package com.bangkit.motix.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.bangkit.motix.data.Repository
import com.bangkit.motix.data.di.Injection

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            synchronized(this) {
                instance = Injection.provideRepository(context)?.let { ViewModelFactory(it) }
            }
            return instance as ViewModelFactory
        }
    }
}