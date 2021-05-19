package com.example.apbd.jobScheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import com.example.apbd.MEMES_DATA
import com.example.apbd.MEMES_NETWORK
import com.example.apbd.data.Memes
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MemeScheduler : JobService() {

    val url = "https://api.imgflip.com/get_memes"

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        GetMemeData(params)
        return true
    }

    private fun GetMemeData(params: JobParameters?) {
        var client = AsyncHttpClient()
        val charset = Charsets.UTF_8

        var handler = object :  AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                var result = responseBody?.toString(charset) ?: "No Data"
                var jsonObject = JSONObject(result)
                var memesArray = jsonObject.getJSONObject("data").getJSONArray("memes")

                var index = (0..memesArray.length() - 1).random()
                var intentMemes = Intent(MEMES_NETWORK)
                intentMemes.putExtra(MEMES_DATA,Memes(
                        memesArray.getJSONObject(index).getString("name"),
                        memesArray.getJSONObject(index).getString("url")
                ))
                Log.i("RESULTS",memesArray.toString())
                sendBroadcast(intentMemes)
                jobFinished(params,true)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                jobFinished(params,true)
            }
        }

        client.get(url,handler)
    }
}