package com.example.apbd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

public class incomeAdapter():RecyclerView.Adapter<myholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        return myholder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycle_expense,parent ,false)
        )
    }

    override fun onBindViewHolder(holder: myholder, position: Int) {

    }

    override fun getItemCount(): Int = 1

}

class myholder(view: View) : RecyclerView.ViewHolder(view){

}