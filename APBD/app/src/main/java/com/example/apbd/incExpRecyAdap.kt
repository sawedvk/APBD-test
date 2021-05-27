package com.example.apbd

import android.content.Context
import android.content.Intent
import android.util.Log
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
        Log.i("IDnya",IncomeList[position]._Id.toString())
        holder.nominal.text = IncomeList[position].Amount
        holder.deskripsi.text = IncomeList[position].Desc
        holder.tanggal.text = IncomeList[position].Date
        holder.itemView.setOnClickListener {
            var intentIncome = Intent(context, income::class.java)
            intentIncome.putExtra("Deskripsi", IncomeList[position].Desc)
            intentIncome.putExtra("Tanggal", IncomeList[position].Date)
            intentIncome.putExtra("Nominal", IncomeList[position].Amount)
            intentIncome.putExtra("Id", IncomeList[position]._Id.toString())
            context.startActivity(intentIncome)
        }
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