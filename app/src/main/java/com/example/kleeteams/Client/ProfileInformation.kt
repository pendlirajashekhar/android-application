package com.example.kleeteams.Client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityProfileInformationBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ProfileInformation : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private var database = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var companyName = ""
    var scopeofwork = ""
    private lateinit var binding: ActivityProfileInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityProfileInformationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        actionBar =supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)
        binding.submitbutton.setOnClickListener {
            profileinformation()
        }
    }
    private fun profileinformation(){
        companyName = binding.companyname.text.toString()
        scopeofwork = binding.scopofwork.text.toString()
        if (companyName.isEmpty()){
            binding.companyname.error = "enter you companyname"
        }else if (scopeofwork.isEmpty()){
            binding.scopofwork.error = "enter scope of work"
        }else{
           // startActivity(Intent(this,ClientHome::class.java))
            insertData()
        }
    }

    private fun insertData() {
        var profile = database.getReference("profileinformation").child(onlyDate()).child(onlyTime())
        profile.child("companyname").setValue( binding.companyname.text.toString())
        profile.child("scopeofwork").setValue( binding.scopofwork.text.toString())
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