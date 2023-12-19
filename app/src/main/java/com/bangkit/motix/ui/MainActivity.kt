package com.bangkit.motix.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bangkit.motix.R
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
                if(this.inputLinkEditText.text.toString().isEmpty()){
                    Toast.makeText(this@MainActivity,
                        resources.getString(R.string.empty_text_warning),Toast.LENGTH_SHORT).show()
                }else{
                    val text:String = this.inputLinkEditText.text.toString()
                    viewModel.checkDetect(text)
                }
            }
        }
        viewModel.response.observe(this){
            Log.d("Observe", it.valid.validate)
            if(it.valid.validate == "1"){
                binding.imageResult.setImageResource(R.drawable.icon_not_verified)
                binding.tvValidInvalid.text = resources.getString(R.string.invalid)

            }else if(it.valid.validate == "0") {
                binding.imageResult.setImageResource(R.drawable.icon_verified)
                binding.tvValidInvalid.text = resources.getString(R.string.valid)

            }
        }
        setThemeLightOnly()
    }


    private fun setThemeLightOnly(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}