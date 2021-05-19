package com.example.apbd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculate.*

class calculate : AppCompatActivity(),MainVPInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        Konsumsi.isChecked = true
        var pil = 1
        var presenter = MainPresenter(this)

        radioGroup?.setOnCheckedChangeListener { radioGroup, checkedId ->
            pil = when(checkedId){
                R.id.Konsumsi -> 1
                R.id.Transportasi -> 2
                else -> 3
            }
        }

        Proses1.setOnClickListener {
            var nominal:String = Nominal.text.toString()
            if(nominal.isEmpty()){
                Nominal.setError("Tidak Boleh Kosong")
            }
            else{
                var convertNominal:Double = nominal.toDouble()
                presenter.hitungtujuh(convertNominal,pil)
            }
        }

        Proses2.setOnClickListener {
            var nominal:String = Nominal.text.toString()
            if(nominal.isEmpty()){
                Nominal.setError("Tidak Boleh Kosong")
            }
            else{
                var convertNominal:Double = nominal.toDouble()
                presenter.hitungempatbelas(convertNominal,pil)
            }
        }

        Proses3.setOnClickListener {
            var nominal:String = Nominal.text.toString()
            if(nominal.isEmpty()){
                Nominal.setError("Tidak Boleh Kosong")
            }
            else{
                var convertNominal:Double = nominal.toDouble()
                presenter.hitungsebulan(convertNominal,pil)
            }
        }
    }

    override fun hasiltujuh(model: MainModel) {
        result.text = model.tujuhhari.toString()
    }

    override fun hasilempatbelas(model: MainModel) {
        result.text = model.empatbelashari.toString()
    }

    override fun hasilsebulan(model: MainModel) {
        result.text = model.satubulan.toString()
    }
}