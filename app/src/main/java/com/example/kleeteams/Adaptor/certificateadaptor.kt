package com.example.kleeteams.Adaptor

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kleeteams.R
import org.w3c.dom.Text

class certificateadaptor(private val context: Activity, private val arrayList: ArrayList<certificatedata>):
    ArrayAdapter<certificatedata>(context,
        R.layout.certificatelist,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.certificatelist, null)

        val name: TextView = view.findViewById(R.id.namecertid)
        val applicationproject: TextView = view.findViewById(R.id.appprojectid)
        val applicationplatform: TextView = view.findViewById(R.id.appplatformid)
        val message: TextView = view.findViewById(R.id.messagecertid)
        val img: ImageView = view.findViewById(R.id.certificates1)

        name.text = arrayList[position].name
        applicationproject.text = arrayList[position].applicationproject
        applicationplatform.text = arrayList[position].applicationplatform
        message.text = arrayList[position].message
        img.setImageResource(arrayList[position].certificate)

        return view
    }

}