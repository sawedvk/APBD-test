package com.example.apbd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apbd.data.Income

class IncomeAdapter(val context:Context, val IncomeList: List<Income>):RecyclerView.Adapter<Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycle_expense,parent ,false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.nominal.text = IncomeList[position].Amount
        holder.deskripsi.text = IncomeList[position].Desc
        holder.tanggal.text = IncomeList[position].Date
    }

    override fun getItemCount(): Int = IncomeList.size

}

class Holder(view: View) : RecyclerView.ViewHolder(view){
    var deskripsi : TextView
    var tanggal : TextView
    var nominal : TextView

    init {
        deskripsi= view.findViewById(R.id.Deskripsi)
        tanggal = view.findViewById(R.id.Tanggal)
        nominal = view.findViewById(R.id.Nominal)
    }
}