package com.example.kleeteams.Client

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityTicketSystemBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class TicketSystem : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    //data base calling
    private var database =Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var subject = ""
    var message = ""
    var uid = ""
    var name = ""
    private val shared = "kleeteams"
    private lateinit var binding: ActivityTicketSystemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTicketSystemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        actionBar =supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
        uid = sharedPreferences.getString("uid","default").toString()
        name = sharedPreferences.getString("name","default").toString()
        binding.raise.setOnClickListener {
            ticketsystem()
        }
    }

    private fun ticketsystem() {
        subject = binding.subject.text.toString()
        message = binding.message.text.toString()
        if (subject.isEmpty()) {
            binding.subject.error = "Enter Your Name"
        } else if (message.isEmpty()) {
            binding.message.error = "Eneter Your Message"
        } else {
            // startActivity(Intent(this,ClientHome::class.java))
            insertData()
        }

    }

    //insert database
    private fun insertData() {
        //cal the database
        //  val systemticket = database.getReference("A").child("B").setValue("a")
        val Ts = database.getReference("TicketSystem").child(uid)
        Ts.child("Name").setValue(name)
        Ts.child("Subject").setValue(binding.subject.text.toString())
        Ts.child("Message").setValue(binding.message.text.toString())
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