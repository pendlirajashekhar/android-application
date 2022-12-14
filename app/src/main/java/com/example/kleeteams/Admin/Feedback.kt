package com.example.kleeteams.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Adaptor.Feedbackadaptor
import com.example.kleeteams.Adaptor.Feedbackdata
import com.example.kleeteams.Adaptor.clientinformationadaptor
import com.example.kleeteams.Adaptor.clientinformationdata
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityFeedbackBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Feedback : AppCompatActivity() {
    private var database1 =
        Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var feedbackarraylist : ArrayList<Feedbackdata>
    private lateinit var binding: ActivityFeedbackBinding
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar = supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        binding.swiperfresh.setOnRefreshListener {
            Handler().postDelayed({
                refreshTofinish()
                binding.swiperfresh.isRefreshing = false
            }, 1000)
        }
        feedbackarraylist = ArrayList()
        database1.getReference("TicketSystem").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (baby in snapshot.children) {
                    val mainarray = Feedbackdata(
                        baby.child("Name").value.toString(),
                        baby.child("subject").value.toString(),
                        baby.child("message").value.toString())
                    feedbackarraylist.add(mainarray)
                }
                feedbackarraylist.reverse()
                binding.feedback.adapter =
                    Feedbackadaptor(this@Feedback, feedbackarraylist)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun refreshTofinish() {
        startActivity(intent)
        finish()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}