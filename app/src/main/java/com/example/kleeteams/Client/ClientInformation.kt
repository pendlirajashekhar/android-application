package com.example.kleeteams.Client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityClientInformation2Binding
import com.example.kleeteams.databinding.ActivityClientInformationBinding
import com.example.kleeteams.databinding.ActivityInformationBinding
import com.example.kleeteams.databinding.ActivityProfileInformationBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class ClientInformation : AppCompatActivity() {
    private var database = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var dataofincorporation = ""
    var noofemployees = ""
    var companyname = ""
    var companyaddress = ""
    private lateinit var actionBar: ActionBar
    private lateinit var binding: ActivityClientInformation2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientInformation2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitbutton.setOnClickListener {
            clientinformation()
        }
    }

    private fun clientinformation() {
        dataofincorporation = binding.dateofincorporation.text.toString()
        noofemployees = binding.noofemployees.text.toString()
        companyname= binding.companyname.text.toString()
        companyaddress = binding.companyadd.text.toString()
        if (dataofincorporation.isEmpty()){
            binding.dateofincorporation.error = "Enter Your date of incorporation"
        }else if (noofemployees.isEmpty()){
            binding.noofemployees.error = "enter your total employees"
        }else if (companyname.isEmpty()){
            binding.companyname.error = "Enter Your companyname"
        }else if (companyaddress.isEmpty()){
            binding.companyadd.error = "Enter your companyaddress"
        }else{
            //startActivity(Intent(this,ClientHome::class.java))
            insertData()
        }
    }

    private fun insertData() {
        val projectinfo = database.getReference("clientinformation").child(onlyDate()).child(onlyTime())
        projectinfo.child("dateofincorporation").setValue(binding.dateofincorporation.text.toString())
        projectinfo.child("noofemployees").setValue(binding.noofemployees.text.toString())
        projectinfo.child("companyname").setValue(binding.companyname.text.toString())
        projectinfo.child("companyaddress").setValue(binding.companyadd.text.toString())
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