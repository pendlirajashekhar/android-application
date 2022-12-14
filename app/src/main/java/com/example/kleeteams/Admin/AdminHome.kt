package com.example.kleeteams.Admin

import android.app.Notification
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kleeteams.Authentication.Login
import com.example.kleeteams.Profile
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityAdmineHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AdminHome : AppCompatActivity() {
    private val shared = "kleeteams"
    private lateinit var binding: ActivityAdmineHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdmineHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.approvalbutton.setOnClickListener {
            var ab = Intent(this, Approve::class.java)
            startActivity(ab)
        }
        binding.Clientinformationbutton.setOnClickListener {
            var cfb = Intent(this, ClientInformation::class.java)
            startActivity(cfb)
        }
        binding.interninformationbutton.setOnClickListener {
            var cif = Intent(this, InternInformation::class.java)
            startActivity(cif)
        }
        binding.Notificationsbutton.setOnClickListener {
            var nb = Intent(this, Feedback::class.java)
            startActivity(nb)
        }
        binding.Profilebutton.setOnClickListener {
            var pb = Intent(this, Profile::class.java)
            startActivity(pb)
        }
        binding.issuecertificate.setOnClickListener {
            var ic = Intent(this, IssueCertificate::class.java)
            startActivity(ic)
        }
        binding.internworkinfo.setOnClickListener {
            var iwi = Intent(this, InternWorkInfo::class.java)
            startActivity(iwi)
        }
        binding.logoutbutton.setOnClickListener {
           // var lgb = Intent(this,Login::class.java)
            //startActivity(lgb)

            MaterialAlertDialogBuilder(this)
                .setTitle("logout")
                .setMessage("are you sure logout")
                .setNegativeButton("no") { dialog, which ->
                }
                .setPositiveButton("yes"){
                    dialog, which->
                    Firebase.auth.signOut()
                    val sharedPreferences: SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.clear()
                    startActivity(Intent(this,Login::class.java))
                }
                .show()
        }
    }
}
