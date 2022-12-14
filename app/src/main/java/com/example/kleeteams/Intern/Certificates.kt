package com.example.kleeteams.Intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Adaptor.Workdeatilsadaptor
import com.example.kleeteams.Adaptor.Workdetailsdata
import com.example.kleeteams.Adaptor.certificateadaptor
import com.example.kleeteams.Adaptor.certificatedata
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityCertificatesBinding
import com.example.kleeteams.databinding.ActivityWorkDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Certificates : AppCompatActivity() {
    private var database1 = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var certificatelistarray : ArrayList<certificatedata>
    private lateinit var binding:ActivityCertificatesBinding
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCertificatesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.swiperfresh.setOnRefreshListener {
            Handler().postDelayed({
                refreshTofinish()
                binding.swiperfresh.isRefreshing = false
            },1000)
        }
        certificatelistarray = ArrayList()

        database1.getReference("IssueCertificates").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(baby in snapshot.children){
                    val mainarray=certificatedata(baby.child("name").value.toString(),
                        baby.child(
                                "applicationproject").value.toString(),
                        baby.child("applicationplatform").value.toString(),
                        baby.child(
                                "messege").value.toString(),
                        R.drawable.certificate)
                    certificatelistarray .add(mainarray)
                }
                certificatelistarray
                binding.certificate.adapter= certificateadaptor(this@Certificates,certificatelistarray)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

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

