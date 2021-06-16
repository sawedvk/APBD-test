package com.example.apbd


import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_banner_ads.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home.expense
import kotlinx.android.synthetic.main.home.income
import java.util.*

class Home : AppCompatActivity() {
    val sharestuff = SharedPref(this, "itemData")
    private lateinit var mySharedPrefWidget: SharedPrefWidget

    var mAlarmManager: AlarmManager?=null
    var mPendingIntent: PendingIntent?=null

//    fun goToHome1(view: View) {
//        var intenthome = Intent(this,Home::class.java)
//        startActivity(intenthome)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        MobileAds.initialize(this){}

        adView2.loadAd(AdRequest.Builder().build())
        adView2.adListener = object : AdListener(){

        }

        mySharedPrefWidget = SharedPrefWidget(this)

        Gotohome.setOnClickListener {
            var intenthome = Intent(this,Home::class.java)
            startActivity(intenthome)
        }

        Log.i("HOME","HomeCreated")

        var usr:User? = intent.getParcelableExtra<User>(EXTRA_USER)
        hai_name_.setText("Hello ${usr?.username} ")

        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        item_Date.text = sharestuff.getDate()
        item_Name.text = sharestuff.getProduct()
        item_Price.text = sharestuff.getPrice().toString()

        mySharedPrefWidget.nama = item_Name.text.toString()
        mySharedPrefWidget.tanggal = item_Date.text.toString()
        mySharedPrefWidget.harga = item_Price.text.toString()
        var appWidgetManager = AppWidgetManager.getInstance(this)
        var ids : IntArray = appWidgetManager.getAppWidgetIds(ComponentName(this,SharedPrefWidget::class.java))
        var updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids)
        sendBroadcast(updateIntent)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
//            R.id.memes -> {
//                val intent = Intent(this, Memes::class.java)
//                startActivity(intent)
//            }
            R.id.Contact -> {
                val intent = Intent(this,ActivityDetail::class.java)
                startActivity(intent)
            }
            R.id.Calculate -> {
                val intent = Intent(this,calculate::class.java)
                startActivity(intent)
            }
            R.id.MediaPlayer -> {
                val intent = Intent(this, MediaPlayer::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }



    fun goToIncome(view: View) {
        var intentIncome = Intent(this, income::class.java)
        startActivity(intentIncome)
    }
    fun goToExpense(view: View) {
        var intentexpense = Intent(this, expense::class.java)
        startActivity(intentexpense)
    }

    fun goToPlan(view: View) {
        var alarmTimer = Calendar.getInstance()
        alarmTimer.add(Calendar.SECOND, 5)
        var sendIntent = Intent(this, MyReceiverAlarm::class.java)
        mPendingIntent = PendingIntent.getBroadcast(this, 101, sendIntent, 0)
        mAlarmManager?.set(AlarmManager.RTC, alarmTimer.timeInMillis, mPendingIntent)
        var intenPlan = Intent(this,Planning::class.java)
        startActivity(intenPlan)
    }
    fun goToHistory(view: View) {
        var intenthistory = Intent(this,history::class.java)
        startActivity(intenthistory)
    }

    fun goToSettings(view: View) {
        var intentsettings = Intent(this,Settings::class.java)
        startActivity(intentsettings)
    }
    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
            .setMessage("New Transaction")
            .setPositiveButton("Income", DialogInterface.OnClickListener{ dialogInterface, i -> goToIncome(view)
            })
            .setNegativeButton("Expense", DialogInterface.OnClickListener{ dialogInterface, i -> goToExpense(view)
            })
        dialog.show()
    }
}