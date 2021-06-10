package com.example.apbd

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class HistoryWidget : AppWidgetProvider() {


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val thisAppWidgetComponenName = ComponentName(context!!.packageName, javaClass.name)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidgetComponenName)
        for(appWidgetId in appWidgetIds){
            updateAppWidget(context, appWidgetManager,appWidgetId)
        }
    }

    companion object{
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val mySharedPrefWidget = SharedPrefWidget(context)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.history_widget)
            views.setTextViewText(R.id.Deskripsi,mySharedPrefWidget.nama.toString())
            views.setTextViewText(R.id.Tanggal,mySharedPrefWidget.tanggal.toString())
            views.setTextViewText(R.id.Nominal,mySharedPrefWidget.harga.toString())
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

