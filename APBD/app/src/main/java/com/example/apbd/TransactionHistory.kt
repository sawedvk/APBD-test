package com.example.apbd

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class TransactionHistory : AppWidgetProvider() {
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
    }

    companion object{
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.transaction_history)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            views.setOnClickPendingIntent(R.id.transcationHist,
            PendingIntent.getActivity(context, 125 , Intent(context,history::class.java),PendingIntent.FLAG_UPDATE_CURRENT))

            views.setOnClickPendingIntent(R.id.buttonExpense,
                PendingIntent.getActivity(context, 124 , Intent(context,expense::class.java),PendingIntent.FLAG_UPDATE_CURRENT ))

            views.setOnClickPendingIntent(R.id.buttonIncome,
                PendingIntent.getActivity(context, 123 , Intent(context,income::class.java),PendingIntent.FLAG_UPDATE_CURRENT))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
