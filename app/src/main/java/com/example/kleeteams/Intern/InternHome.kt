package com.example.kleeteams.Intern

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kleeteams.Authentication.Login
import com.example.kleeteams.Profile
import com.example.kleeteams.databinding.ActivityInternHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InternHome : AppCompatActivity() {
    private var shared= "kleeteams"
    private lateinit var binding:ActivityInternHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addprofile.setOnClickListener {
            var adb = Intent(this,AddProfile::class.java)
            startActivity(adb)
        }
        binding.certificates.setOnClickListener {
            var ib = Intent(this,Certificates::class.java)
            startActivity(ib)
        }
        binding.information.setOnClickListener {
            var cb = Intent(this,Information::class.java)
            startActivity(cb)
        }
        binding.workdetails.setOnClickListener {
            var wb = Intent(this,WorkDetails::class.java)
            startActivity(wb)
        }
        binding.profile.setOnClickListener {
            var pb = Intent(this,Profile::class.java)
            startActivity(pb)
        }
        binding.logout.setOnClickListener {
            // var lgb = Intent(this,Login::class.java)
            //startActivity(lgb)

            MaterialAlertDialogBuilder(this)
                .setTitle("logout")
                .setMessage("are you sure logout")
                .setNegativeButton("no") { dialog, which ->
                }
                .setPositiveButton("yes") { dialog, which ->
                    Firebase.auth.signOut()
                    val sharedPreferences: SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.clear()
                    startActivity(Intent(this, Login::class.java))
                }
                .show()
        }
    }
}