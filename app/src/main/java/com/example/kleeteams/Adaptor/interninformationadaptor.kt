package com.example.kleeteams.Adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kleeteams.R

class interninformationadaptor(private val context: Activity, private val arrayList: ArrayList<interninformationdata>):
    ArrayAdapter<interninformationdata>(context,
        R.layout.informationlist,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.informationlist,null)

        val name: TextView = view.findViewById(R.id.nameid)
        val email: TextView = view.findViewById(R.id.emailid)
        val cname: TextView = view.findViewById(R.id.cnameid)
        val phone: ImageView = view.findViewById(R.id.phonebutton)

        name.text = arrayList[position].name
        email.text = arrayList[position].email
        cname.text= arrayList[position].cname
        phone.setImageResource(arrayList[position].phone)
        //phone.text=arrayList[position].phone

        return view
    }
}