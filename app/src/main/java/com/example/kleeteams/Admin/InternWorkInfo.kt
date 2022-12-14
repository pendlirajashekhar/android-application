package com.example.kleeteams.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityInternWorkInfoBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class InternWorkInfo : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private var database = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var projectName = ""
    var projectplatform = ""
    var stipend = ""
    private lateinit var binding:ActivityInternWorkInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityInternWorkInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        actionBar = supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        setContentView(binding.root)
        binding.postbutton.setOnClickListener {
           internworkinfo()
        }
    }
    private fun internworkinfo() {
        projectName = binding.projname.text.toString()
        projectplatform = binding.projplatform.text.toString()
        stipend = binding.stipend.text.toString()
        if (projectName.isEmpty()) {
            binding.projname.error = "enter project name"
        } else if (projectplatform.isEmpty()) {
            binding.projplatform.error = "enter the platform"
        } else if (stipend.isEmpty()) {
            binding.stipend.error = "enter your stipend"
        } else {
           // startActivity(Intent(this, AdminHome::class.java))
            insertData()
        }

    }

    private fun insertData() {
        val internwork = database.getReference("intern workinfo").child(onlyDate()).child(onlyTime())
        internwork.child("projectname").setValue( binding.projname.text.toString())
        internwork.child("projectplatform").setValue( binding.projplatform.text.toString())
        internwork.child("stipend").setValue( binding.stipend.text.toString())
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