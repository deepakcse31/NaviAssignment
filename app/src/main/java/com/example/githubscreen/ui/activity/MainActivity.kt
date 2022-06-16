package com.example.githubscreen.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.githubscreen.R
import com.example.githubscreen.databinding.ActivityMainBinding
import com.example.githubscreen.network.NetworkConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding?=null
    @Inject
    lateinit var networkConnectionLiveData : NetworkConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        networkConnectionLiveData.observe(this){
            if (it.equals(false))
            {
                Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
            }
        }
        setContentView(binding!!.root)
    }
}