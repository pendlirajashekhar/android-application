package com.example.kleeteams.Admin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityIssueCertificateBinding
import com.example.kleeteams.databinding.ActivityTicketSystemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class IssueCertificate : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth
    private var database = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var applicationproject = ""
    var applicationplatform = ""
    var message = ""
    var uid = ""
    var name = ""
    private val shared = "kleeteams"
    private lateinit var binding: ActivityIssueCertificateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIssueCertificateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        actionBar = supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        binding = ActivityIssueCertificateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
        uid = sharedPreferences.getString("uid","default").toString()
        name = sharedPreferences.getString("name","default").toString()
        binding.issue.setOnClickListener {
            issuecertficateacces()
        }
    }
    private fun issuecertficateacces(){
        applicationproject = binding.appproject.text.toString()
        applicationplatform = binding.appplatform.text.toString()
        message = binding.message.text.toString()
        if(applicationproject.isEmpty()){
            binding.appproject.error = "Enter your applicationproject"
        }else if (applicationplatform.isEmpty()){
            binding.appplatform.error = "Enter your applicationplatform"
        }else if (message.isEmpty()){
            binding.message.error = "Enter your message"
        }else{
           // startActivity(Intent(this,AdminHome::class.java))
            insertData()
        }
    }
    private fun insertData() {
        val issue = database.getReference("IssueCertificate").child(uid)
        issue.child("name").setValue(name)
        issue.child("applicationproject").setValue( binding.appproject.text.toString())
        issue.child("applicationplatform").setValue( binding.appplatform.text.toString())
        issue.child("messege").setValue( binding.message.text.toString())
        refreshTofinish()
    }
    fun onlyDate(): String {
        var calendar: Calendar
        var simpleDateFormat: SimpleDateFormat
        var date: String
        var fData: String
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        date = simpleDateFormat.format(calendar.time)
        fData = date.subSequence(0, 10).toString()
        return fData
    }

    fun onlyTime(): String {
        var calendar: Calendar
        var simpleDateFormat: SimpleDateFormat
        var date: String
        var fData: String
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        date = simpleDateFormat.format(calendar.time)
        fData = date.subSequence(11, 19).toString()
        return fData
    }

    fun currentTimeNDate(): String {
        var calendar: Calendar
        var simpleDateFormat: SimpleDateFormat
        var date: String
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        date = simpleDateFormat.format(calendar.time)
        return date
    }
    fun refreshTofinish(){
        startActivity(intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}