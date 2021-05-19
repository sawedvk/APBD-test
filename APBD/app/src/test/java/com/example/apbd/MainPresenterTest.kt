package com.example.apbd

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class MainPresenterTest {

    private var view:MainVPInterface = mock(MainVPInterface::class.java)
    private var presenter = MainPresenter(view)

    @Test
    fun hitungtujuh() {
        var tujuhhari:Double = presenter.tujuh(15000.0,1)
        assertEquals(315000.0,tujuhhari,0.001)
    }

    @Test
    fun hitungempatbelas() {
        var empatbelashari:Double = presenter.empatbelas(15000.0,1)
        assertEquals(630000.0,empatbelashari,0.001)
    }

    @Test
    fun hitungsebulan() {
        var sebulan:Double = presenter.sebulan(15000.0,1)
        assertEquals(1350000.0,sebulan,0.001)
    }
}