package com.example.kleeteams.Adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kleeteams.R

class Feedbackadaptor(private val context: Activity, private val arrayList: ArrayList<Feedbackdata>):
    ArrayAdapter<Feedbackdata>(context,
    R.layout.feedbacklist,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //call xml file
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.feedbacklist, null)

        //declare elements
        val subject: TextView = view.findViewById(R.id.subjectid)
        val message: TextView = view.findViewById(R.id.messageid)
        val name: TextView = view.findViewById(R.id.nameid)
        name.text = arrayList[position].Name

        subject.text = arrayList[position].subject
        message.text = arrayList[position].message

        return view
    }
}