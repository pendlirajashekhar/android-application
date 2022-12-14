package com.example.kleeteams.Authentication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.example.kleeteams.Admin.AdminHome
import com.example.kleeteams.Client.ClientHome
import com.example.kleeteams.Intern.InternHome
import com.example.kleeteams.R
import com.example.kleeteams.databinding.ActivityAdmineHomeBinding
import com.example.kleeteams.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {
    //var radioGroup:RadioGroup?=null
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    private var selectedRadioButton = ""
    private lateinit var firebaseAuth: FirebaseAuth
    var modules = ""
    private var database1 = Firebase.database("https://kleeteams-default-rtdb.asia-southeast1.firebasedatabase.app/")
    var name = ""
    var email = ""
    var mobilenumber = ""
    var createpassword = ""
    var conformpasword = ""
    var gender=""
    private val shared = "kleeteams"
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //radio
        radioGroup=findViewById(R.id.radiogroup)

        var module = resources.getStringArray(R.array.modules)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdownitem,module)
        binding.modules.setAdapter(arrayAdapter)
        binding.modules.setOnItemClickListener { adapterView, view, i, l ->
            modules = adapterView.getItemAtPosition(i).toString()
        }

        binding.login.setOnClickListener {
            var login = Intent(this, Login::class.java)
            startActivity(login)
        }
        binding.singin.setOnClickListener {
            // var sing = Intent(this,ClientHome::class.java)
          //  startActivity(sing)
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(intSelectButton)
            selectedRadioButton = radioButton.text.toString()
            //Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()
            registeraccess()
        }
    }
    private fun registeraccess() {
        name = binding.name.text.toString()
        email = binding.email.text.toString()
        mobilenumber = binding.mobilenumber.text.toString()
        createpassword = binding.createpassword.text.toString()
        conformpasword = binding.conformpassword.text.toString()
        if (name.isEmpty()) {
            binding.name.error = "Enter Your Name"
        } else if (email.isEmpty()) {
            binding.email.error = "Enter your Email"
        } else if (mobilenumber.isEmpty()) {
            binding.mobilenumber.error = "Enter Your Mobile number"
        } else if (!conformpasword.equals(createpassword) || conformpasword.length <= 6) {
            binding.createpassword.error = "Enter Your password"
        } else {

          //var register = Intent(this, InternHome::class.java)
         // startActivity(register)
            firebasesignup()
        }
    }

    private fun insertdata(uniqueid:String) {
        val reg = database1.getReference("register").child(uniqueid)
        var name = reg.child("name").setValue(binding.name.text.toString())
        var email =  reg.child("email").setValue(binding.email.text.toString())
        var mobilenumber = reg.child("mobilenumber").setValue(binding.mobilenumber.text.toString())
        var module = reg.child("module").setValue(modules)
        var gender = reg.child("gender").setValue(selectedRadioButton)
        var timestamp = reg.child("timestamp").setValue(currentTimeNDate())
        sharedPreferences(name.toString()
            ,email.toString(),mobilenumber.toString(),module.toString(),timestamp.toString(),gender.toString())

        startActivity(Intent(this,Login::class.java))
        refreshTofinish()
        when(modules){
            "client" ->
                startActivity(Intent(this,ClientHome::class.java))
            "intern" ->
                startActivity(Intent(this,InternHome::class.java))
        }
    }

    private fun firebasesignup() {
        firebaseAuth.createUserWithEmailAndPassword(binding.email.text.toString(),binding.createpassword.text.toString())
            .addOnSuccessListener {
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                val uniqueid = firebaseUser!!.uid
                insertdata(uniqueid)
            }
            .addOnFailureListener {
                Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
            }
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
    fun refreshTofinish() {
        startActivity(intent)
        finish()
    }
    fun sharedPreferences(name:String,email:String,mobilenumber:String,module:String,timestamp:String,gender:String){
        //mode set
        val sharedPreferences:SharedPreferences = this.getSharedPreferences(shared, MODE_PRIVATE)
        //editor
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("name",name)
        editor.putString("email",email)
        editor.putString("mobile",mobilenumber)
        editor.putString("module",module)
        editor.putString("ts",timestamp)
        editor.putString("gender",selectedRadioButton)
        editor.apply()
        editor.commit()

    }

}
