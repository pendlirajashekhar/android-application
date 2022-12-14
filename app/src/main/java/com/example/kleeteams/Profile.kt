package com.example.kleeteams

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Client.ClientHome
import com.example.kleeteams.databinding.ActivityProfileBinding
import java.util.regex.Pattern

class Profile : AppCompatActivity() {
    var emailid = ""
    var mobileno = ""
    var gender = ""
    var address = ""
    var names = ""
    private val shared = "kleetemes"
    private lateinit var binding: ActivityProfileBinding
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        actionBar=supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        setContentView(binding.root)
        //mode set
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
        //editor
        //Toast.makeText(this,sharedPreferences.getString("name","default"), Toast.LENGTH_SHORT).show()
        var emailid = sharedPreferences.getString("emailid","default")
        binding.emailid.text=names
        var mobileno = sharedPreferences.getString("mobileno","default")
        binding.mobileno.text=names
        var gender = sharedPreferences.getString("gender","default")
        binding.gender.text=names
        var address = sharedPreferences.getString("address","default")
        binding.address.text=names
        var names = sharedPreferences.getString("name","default")
        binding.names.text=names

        binding.subbmit.setOnClickListener {
           // Profile()
        }
    }
    private fun profile(){
        emailid = binding.emailid.text.toString()
        mobileno = binding.mobileno.text.toString()
        address = binding.address.text.toString()
        names = binding.names.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
            binding.emailid.error = "enter mail id"
        }else if (names.isEmpty()){
            binding.names.error = " enter your name"
        }else if (mobileno.isEmpty()){
            binding.mobileno.error = "enter mobile no"
        }else if (gender.isEmpty()){
            binding.gender.error = "enter your gender"
        }else if (address.isEmpty()){
            binding.address.error = "enter address"
        }else{
            startActivity(Intent(this,ClientHome::class.java))
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}