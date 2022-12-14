package com.example.kleeteams.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Adaptor.interninformationadaptor
import com.example.kleeteams.Adaptor.interninformationdata
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityInternInformationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class InternInformation : AppCompatActivity() {

    private var database1 =
        Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var informationarraylist: ArrayList<interninformationdata>
    private lateinit var actionBar: ActionBar
    private lateinit var binding: ActivityInternInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.setTitle("back")
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        binding.swiperfresh.setOnRefreshListener {
            Handler().postDelayed({
                refreshTofinish()
                binding.swiperfresh.isRefreshing = false
            }, 1000)
        }
        informationarraylist = ArrayList()

        database1.getReference("register").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (baby in snapshot.children) {
                    val mainarray = interninformationdata(
                        baby.child("name").value.toString(),
                        baby.child("emailid").value.toString(),
                        baby.child("cname").value.toString(), R.drawable.phonecall
                    )
                    informationarraylist.add(mainarray)
                }
                informationarraylist.reverse()
                binding.interninformation.adapter =
                    interninformationadaptor(this@InternInformation, informationarraylist)


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
