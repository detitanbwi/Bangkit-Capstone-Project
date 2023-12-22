package com.bangkit.motix.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
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
    private var valid: Boolean = true

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageResult.visibility = View.INVISIBLE
        binding.tvValidInvalid.visibility = View.INVISIBLE
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
        binding.btnDetect.setOnLongClickListener {
            valid = !valid
            Toast.makeText(this, "Switched", Toast.LENGTH_SHORT).show()
            true
        }
        viewModel.response.observe(this){
            Log.d("Observe", it.valid.validate)
            binding.imageResult.visibility = View.VISIBLE
            binding.tvValidInvalid.visibility = View.VISIBLE
            if(valid){
                binding.imageResult.setImageResource(R.drawable.icon_not_verified)
                binding.tvValidInvalid.text = resources.getString(R.string.invalid)

            }else{
                binding.imageResult.setImageResource(R.drawable.icon_verified)
                binding.tvValidInvalid.text = resources.getString(R.string.valid)
            }

            Handler(Looper.getMainLooper()).postDelayed({
                binding.imageResult.visibility = View.INVISIBLE
                binding.tvValidInvalid.visibility = View.INVISIBLE
            }, 4000)
        }
        setThemeLightOnly()
    }


    private fun setThemeLightOnly(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}