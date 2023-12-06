package com.bangkit.motix.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bangkit.motix.databinding.ActivityMainBinding
import com.bangkit.motix.ui.viewmodel.MainViewModel
import com.bangkit.motix.ui.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setThemeLightOnly()
    }


    private fun setUpAction(){
        binding?.btnDetect?.setOnClickListener {  }
    }

    private fun setThemeLightOnly(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}