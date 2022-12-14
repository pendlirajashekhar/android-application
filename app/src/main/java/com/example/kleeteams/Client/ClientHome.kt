package com.example.kleeteams.Client

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kleeteams.Authentication.Login
import com.example.kleeteams.Profile
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityClientHomeBinding
import com.example.kleeteams.databinding.ActivityInternHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ClientHome : AppCompatActivity() {
    private val shared ="kleeteams"
    private lateinit var binding:ActivityClientHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityClientHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clintinfo.setOnClickListener {
            var ci = Intent(this,ClientInformation::class.java)
            startActivity(ci)
        }
        binding.profileinfo.setOnClickListener {
            var pf = Intent(this,ProfileInformation::class.java)
            startActivity(pf)
        }
        binding.projectinfo.setOnClickListener {
            var proj = Intent(this,ProjectInformation::class.java)
            startActivity(proj)
        }
        binding.ticketsystem.setOnClickListener {
            var ts = Intent(this,TicketSystem::class.java)
            startActivity(ts)
        }
        binding.profile.setOnClickListener {
            var pro = Intent(this,Profile::class.java)
            startActivity(pro)
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