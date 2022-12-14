package com.example.kleeteams.Adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kleeteams.R

class
Workdeatilsadaptor(private val context: Activity, private val arrayList: ArrayList<Workdetailsdata>):
    ArrayAdapter<Workdetailsdata>(context,
    R.layout.workdetaillist,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.workdetaillist,null)

        val work: TextView = view.findViewById(R.id.workid)
        val language: TextView = view.findViewById(R.id.languageid)
        val stipend: TextView = view.findViewById(R.id.stipendid)
        val accept: ImageView = view.findViewById(R.id.acceptid)

        work.text = arrayList[position].work
        language.text = arrayList[position].language
        stipend.text= arrayList[position].stipend
        accept.setImageResource(arrayList[position].accept)

        return view
    }
}