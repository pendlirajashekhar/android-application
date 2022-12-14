package com.example.kleeteams.Client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityProjectInformationBinding
import com.example.kleeteams.databinding.ActivityTicketSystemBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ProjectInformation : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private var database1 = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var projectname = ""
    var projectbudget = ""
    var projectdiscription = ""
    var projectduration = ""
    var programinglanguage = ""
    var database = ""
    private lateinit var binding: ActivityProjectInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar =supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        binding = ActivityProjectInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.createproject.setOnClickListener {
            projectinformation()
        }
    }
    private fun projectinformation(){
        projectname = binding.projectname.text.toString()
        projectbudget = binding.projectbudget.text.toString()
        projectdiscription= binding.projectdiscription.text.toString()
        projectduration = binding.projectduration.text.toString()
        programinglanguage = binding.programinglang.text.toString()
        database = binding.database.text.toString()
        if (projectname.isEmpty()){
            binding.projectname.error = "Enter Your Projectname"
        }else if (projectbudget.isEmpty()){
            binding.projectbudget.error = "Enter Your Budget"
        }else if (projectdiscription.isEmpty()){
            binding.projectdiscription.error = "Enter Your Discription"
        }else if (projectduration.isEmpty()){
            binding.projectduration.error = "Enter your Projectduration"
        }else if (programinglanguage.isEmpty()){
            binding.programinglang.error = "Enter Your Programinglanguage"
        }else if (database.isEmpty()) {
            binding.database.error = "Enter Your Database"
        }else{
            //startActivity(Intent(this,ClientHome::class.java))
            insertData()
        }
    }

    private fun insertData() {
        val projectinfo = database1.getReference("projectinformation").child(onlyDate()).child(onlyTime())
        projectinfo.child("projectname").setValue(binding.projectname.text.toString())
        projectinfo.child("projectbudget").setValue(binding.projectbudget.text.toString())
        projectinfo.child("projectdiscription").setValue(binding.projectdiscription.text.toString())
        projectinfo.child("projectduration").setValue(binding.projectduration.text.toString())
        projectinfo.child("programinglanguage").setValue(binding.programinglang.text.toString())
        projectinfo.child("database").setValue(binding.database.text.toString())
        refreshTofinish()
    }
    fun onlyDate() : String {
        var calendar: Calendar
        var simpleDateFormat: SimpleDateFormat
        var date : String
        var fData : String
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        date = simpleDateFormat.format(calendar.time)
        fData = date.subSequence(0, 10).toString()
        return fData
    }

    fun onlyTime() : String {
        var calendar: Calendar
        var simpleDateFormat: SimpleDateFormat
        var date : String
        var fData : String
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