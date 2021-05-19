package com.example.apbd

class MainPresenter(SetView : MainVPInterface) {
    private var view = SetView
    private var mainModel = MainModel()

    fun tujuh(nominal:Double, pil:Int) = when (pil){
        1 -> 7 * 3 * nominal
        2 -> 7 * 2 * nominal
        else -> 7 * nominal
    }
    fun empatbelas(nominal:Double, pil:Int) = when (pil){
        1 -> 14 * 3 * nominal
        2 -> 14 * 2 * nominal
        else -> 14 * nominal
    }
    fun sebulan(nominal:Double, pil:Int) = when (pil){
        1 -> 30 * 3 * nominal
        2 -> 30 * 2 * nominal
        else -> 30 * nominal
    }
    fun hitungtujuh(nominal:Double, pil:Int) {
        var tujuh:Double = tujuh(nominal,pil)
        mainModel.tujuhhari = tujuh
        view.hasiltujuh(mainModel)
    }
    fun hitungempatbelas(nominal:Double, pil:Int) {
        var empatbelas:Double = empatbelas(nominal,pil)
        mainModel.empatbelashari = empatbelas
        view.hasilempatbelas(mainModel)
    }
    fun hitungsebulan(nominal:Double, pil:Int) {
        var sebulan:Double = sebulan(nominal,pil)
        mainModel.satubulan = sebulan
        view.hasilsebulan(mainModel)
    }

}