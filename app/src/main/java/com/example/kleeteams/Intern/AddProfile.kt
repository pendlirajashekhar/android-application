package com.example.kleeteams.Intern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Client.ClientHome
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityAddProfileBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class AddProfile : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    private var database = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var collegename = ""
    var qualification = ""
    var branch = ""
    var age = ""
    var highqulificationpercentage = ""
    var technicallanguage = ""
    private lateinit var bindding:ActivityAddProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar =supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        bindding=ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(bindding.root)
        bindding.update.setOnClickListener {
           // startActivity(Intent(this,InternHome::class.java))
            addprofileaccess()
        }
    }
    private fun addprofileaccess(){
        collegename = bindding.collegename.text.toString()
        qualification = bindding.qualification.text.toString()
        branch = bindding.branch.text.toString()
        age = bindding.age.text.toString()
        highqulificationpercentage = bindding.hqpercentage.text.toString()
        technicallanguage = bindding.tclanguage.text.toString()
        if (collegename.isEmpty()){
            bindding.collegename.error = "Enter your collegename"
        }else if (qualification.isEmpty()){
            bindding.qualification.error ="Enter your qualification"
        }else if (branch.isEmpty()){
            bindding.branch.error = "Enter your branch"
        }else if (age.isEmpty()){
            bindding.age.error = "Enter your age"
        }else if (highqulificationpercentage.isEmpty()){
            bindding.hqpercentage.error = "Enter your highqualificationpercentage"
        }else if (technicallanguage.isEmpty()){
            bindding.tclanguage.error = "Enter your technicallanguage"
        }else{
            //startActivity(Intent(this,InternHome::class.java))
            insertDate()
        }
    }

    private fun insertDate() {
        val addProfile = database.getReference("AddProfile").child(onlyDate()).child(onlyTime())
        addProfile.child("collegename").setValue(bindding.collegename.text.toString())
        addProfile.child("quqlification").setValue(bindding.qualification.text.toString())
        addProfile.child("branch").setValue(bindding.branch.text.toString())
        addProfile.child("age").setValue(bindding.age.text.toString())
        addProfile.child("highqualificationpercentage").setValue(bindding.hqpercentage.text.toString())
        addProfile.child("technicallanguage").setValue(bindding.tclanguage.text.toString())
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