package com.example.kleeteams.Intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityInformationBinding
import com.example.kleeteams.databinding.ActivityInternInformationBinding
import com.example.kleeteams.databinding.ActivityProfileInformationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Information : AppCompatActivity() {
    private var database = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var dataofincorporation = ""
    var noofemployees = ""
    var companyname = ""
    var companyaddress = ""
    private lateinit var actionBar: ActionBar
    private lateinit var binding: ActivityInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityInformationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        actionBar =supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        setContentView(binding.root)
        binding.sudmitbt.setOnClickListener {
        }

}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}