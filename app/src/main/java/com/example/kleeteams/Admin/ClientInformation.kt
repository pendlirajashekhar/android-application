package com.example.kleeteams.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.kleeteams.Adaptor.*
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityClientInformationBinding
import com.example.kleeteams.databinding.ActivityInformationBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ClientInformation : AppCompatActivity() {

    private var database1 =
        Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private lateinit var informationarraylist : ArrayList<clientinformationdata>
    private lateinit var binding: ActivityClientInformationBinding
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityClientInformationBinding.inflate(layoutInflater)
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
        informationarraylist = ArrayList()
        Toast.makeText(this@ClientInformation,"Inside class",Toast.LENGTH_LONG).show()

        database1.getReference("register").addValueEventListener(object : ValueEventListener {
            //Toast.makeText(this@ClientInformation,"Inside database",Toast.LENGTH_LONG).show()

            override fun onDataChange(snapshot: DataSnapshot) {
                Toast.makeText(this@ClientInformation,"Inside snapshot",Toast.LENGTH_LONG).show()

                for (baby in snapshot.children) {
                    val mainarray = clientinformationdata(
                        baby.child("name").value.toString(),
                        baby.child("email").value.toString(),
                        baby.child("cname").value.toString(), R.drawable.phonecall)
                    informationarraylist.add(mainarray)
                }
                informationarraylist.reverse()
                binding.clientinformation.adapter =
                    clientinformationadaptor(this@ClientInformation,informationarraylist)
            }

            override fun onCancelled(error: DatabaseError) {
Toast.makeText(this@ClientInformation,error.message,Toast.LENGTH_LONG).show()
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