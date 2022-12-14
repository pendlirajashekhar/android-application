package com.example.kleeteams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.inputmethod.InputBinding
import com.example.kleeteams.Authentication.Login
import com.example.kleeteams.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //initialization a=xyz
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({
            startActivity(Intent(this,Login::class.java)
            )
        },1000)
        binding.logoclick.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)

        }
    }
}