package com.example.kleeteams.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.appcompat.view.ActionMode
import com.example.kleeteams.Adaptor.Approvaladaptor
import com.example.kleeteams.Adaptor.Aprovaldata
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityApprove3Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Approve : AppCompatActivity() {
    private var database1 =
        Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")

    private lateinit var aprovearraylist : ArrayList<Aprovaldata>
    private lateinit var actionBar: ActionBar
    private lateinit var binding: ActivityApprove3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar = supportActionBar!!
        actionBar.setTitle("back")
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        binding = ActivityApprove3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //arrY Declaration
       /* val aContact = arrayListOf<String>()
        val aName = arrayListOf<String>()
        val aEmail = arrayListOf<String>()
        val aAccept = arrayListOf<String>()
        val aReject = arrayListOf<String>()
        val aModule = arrayListOf<String>()*/

        binding.swiperfresh.setOnRefreshListener {
            Handler().postDelayed({
                refreshTofinish()
                binding.swiperfresh.isRefreshing = false
            }, 1000)
        }

        //call array list
        aprovearraylist = ArrayList()

        database1.getReference("register").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(baby in snapshot.children){
                   /* aName.add(baby.child("name").value.toString())
                    aContact.add(baby.child("mobilenumber").value.toString())
                    aEmail.add(baby.child("email").value.toString())
                    aModule.add(baby.child("module").value.toString())*/

                    val mainarray=Aprovaldata(baby.child("mobilenumber").value.toString(),
                            baby.child("name").value.toString(),
                        baby.child("email").value.toString(),baby.child("module").value.toString(),"",""
                    )
                    aprovearraylist.add(mainarray)
                }
                aprovearraylist.reverse()
                binding.approver.adapter=Approvaladaptor(this@Approve,aprovearraylist)


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


