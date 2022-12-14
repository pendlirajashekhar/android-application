package com.example.kleeteams.Adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kleeteams.R

class Approvaladaptor(private val context:Activity,private val arrayList: ArrayList<Aprovaldata>):ArrayAdapter<Aprovaldata>(context,
    R.layout.approvelist,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //call xml file
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.approvelist,null)

        //declare elements
        val contact:TextView = view.findViewById(R.id.contact)
        val name:TextView = view.findViewById(R.id.name)
        val email:TextView = view.findViewById(R.id.email)
        val module:TextView = view.findViewById(R.id.moduleapp)
        val accept:ImageView = view.findViewById(R.id.rightclick)
        val rejectt:ImageView = view.findViewById(R.id.wrongclick)

        //imagevariablename.setImageResource(arrayList[position].imageID)
        contact.text = arrayList[position].contact
        //for understanding
       // rejectt.text = arrayList[position].reject
        name.text = arrayList[position].name
        email.text = arrayList[position].email
        module.text= arrayList[position].module
       // accept.text = arrayList[position].accept
        //reject.text = arrayList[position].accept

        return view
    }
}