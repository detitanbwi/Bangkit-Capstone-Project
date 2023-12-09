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

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnDetect.setOnClickListener {
                viewModel.checkDetect(this.inputLinkEditText.text.toString())
            }
        }

        viewModel.response.observe(this){

        }

        setThemeLightOnly()
    }


    private fun setThemeLightOnly(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}