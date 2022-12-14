package com.example.kleeteams.Intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Adaptor.Approvaladaptor
import com.example.kleeteams.Adaptor.Aprovaldata
import com.example.kleeteams.Adaptor.Workdeatilsadaptor
import com.example.kleeteams.Adaptor.Workdetailsdata
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityWorkDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WorkDetails : AppCompatActivity() {
    private var database1 =
        Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var workarraylist : ArrayList<Workdetailsdata>
    private lateinit var actionBar: ActionBar
    private lateinit var binding: ActivityWorkDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar =supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding = ActivityWorkDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.swiperfresh.setOnRefreshListener {
            Handler().postDelayed({
                refreshTofinish()
                binding.swiperfresh.isRefreshing = false
            },1000)
        }
        workarraylist = ArrayList()

        database1.getReference("register").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(baby in snapshot.children){
                    val mainarray=Workdetailsdata(baby.child("work").value.toString(),
                        baby.child("language").value.toString(),
                        baby.child("stipend").value.toString(),R.drawable.workdesign)
                    workarraylist.add(mainarray)
                }
                workarraylist.reverse()
                binding.workdetails.adapter= Workdeatilsadaptor(this@WorkDetails,workarraylist)


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