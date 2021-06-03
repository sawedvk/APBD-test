package com.example.apbd

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apbd.data.ExpenseData

class ExpenseAdapter(val context: Context, val ExpenseList: List<ExpenseData>): RecyclerView.Adapter<HolderExpense>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderExpense {
        return HolderExpense(
                LayoutInflater.from(parent.context).inflate(R.layout.recycle_expense,parent ,false)
        )
    }

    override fun onBindViewHolder(holder: HolderExpense, position: Int) {
        Log.i("IDExpense",ExpenseList[position].id.toString())
        holder.AmountExp.text = ExpenseList[position].amount
        holder.DescExp.text = ExpenseList[position].desc
        holder.DateExp.text = ExpenseList[position].date
        holder.itemView.setOnClickListener {
            var intentExpense = Intent(context, expense::class.java)
            intentExpense.putExtra("Deskripsi", ExpenseList[position].desc)
            intentExpense.putExtra("Tanggal", ExpenseList[position].date)
            intentExpense.putExtra("Nominal", ExpenseList[position].amount)
            intentExpense.putExtra("Id", ExpenseList[position].id.toString())
            context.startActivity(intentExpense)
        }
    }

    override fun getItemCount(): Int = ExpenseList.size

}

class HolderExpense(view: View) : RecyclerView.ViewHolder(view){
    var DescExp : TextView
    var DateExp : TextView
    var AmountExp : TextView

    init {
        DescExp = view.findViewById(R.id.Deskripsi)
        DateExp = view.findViewById(R.id.Tanggal)
        AmountExp = view.findViewById(R.id.Nominal)
    }
}