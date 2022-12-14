package com.example.kleeteams.Authentication

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.kleeteams.Admin.AdminHome
import com.example.kleeteams.Client.ClientHome
import com.example.kleeteams.Intern.InternHome
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityLoginBinding
import com.example.kleeteams.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
class Login : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private var database1 = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var email = ""
    var password = ""
    private val shared = "kleeteams"
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.singupbutton.setOnClickListener {
            var signupaction = Intent(this, Register::class.java)
            startActivity(signupaction)
        }
        binding.Loginbutton.setOnClickListener {
           // var login = Intent(this, AdminHome::class.java)
           // startActivity(login)
            loginAccess()
        }
        binding.forgotbutton.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.kt)
            builder.setTitle("reset password")

            val view = layoutInflater.inflate(R.layout.dialoguepassword, null)
            builder.setView(view)
            builder.setPositiveButton("reset", { dialoginflate, i ->
                //  Toast.makeText(this,"reset password",Toast.LENGTH_LONG).show()
                startActivity(Intent(this, ClientHome::class.java))
            })
            builder.setNegativeButton("cancel", { dialoginflate, i -> })
            builder.show()
        }
    }
    private fun loginAccess() {
        email = binding.useridbutton.text.toString()
        password = binding.passwordidbutton.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.useridbutton.error = "enter email id"
        } else if (password.isEmpty()) {
            binding.passwordidbutton.error = "enter password"
        } else {
            // var  login = Intent(this,AdminHome::class.java)
            //startActivity(login)
            firebaselogin()
        }
    }

    private fun firebaselogin() {
        firebaseAuth.signInWithEmailAndPassword(
            binding.useridbutton.text.toString(),
            binding.passwordidbutton.text.toString()
        )
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                val uniqueid = firebaseUser!!.uid
                database1.getReference("register").child(uniqueid).get().addOnSuccessListener {
                    if (it.exists()){
                        var module=it.child("module").value.toString()
                        var name = it.child("name").value.toString()
                        var email = it.child("email").value.toString()
                        var mobilenumber = it.child("mobilenumber").value.toString()
                        var timestamp = it.child("timestamp").value.toString()
                        Toast.makeText(this, "module", Toast.LENGTH_SHORT).show()
                        when (module) {
                            "client" ->{
                                sharedPreferences(name,uniqueid,email,mobilenumber,module,timestamp)
                                startActivity(Intent(this, ClientHome::class.java))}
                            "intern" ->{
                            sharedPreferences(name,uniqueid,email,mobilenumber,module,timestamp)
                                startActivity(Intent(this, InternHome::class.java))}
                            "Admin" ->{
                                startActivity(Intent(this, AdminHome::class.java))}
                        }
                    }
                    else{
                        Toast.makeText(this, "no user in real time db", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun sharedPreferences(name:String,uid:String,email:String,mobilenumber:String,module:String,timestamp:String){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("uid",uid)
        editor.putString("name",name)
        editor.putString("email",email)
        editor.putString("mobile",mobilenumber)
        editor.putString("module",module)
        editor.putString("ts",timestamp)
        editor.apply()
        editor.commit()
    }
}