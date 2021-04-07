package com.example.apbd

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apbd.data.Memes
import com.example.apbd.jobScheduler.MemeScheduler
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_memes.*

class Memes : AppCompatActivity() {

    val jobID = 13245

    private val MemesReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            val dataMemes = intent?.getParcelableExtra<Memes>(MEMES_DATA)
            Picasso.get().load(dataMemes?.url).into(imageMemes)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memes)

        val filterIntent = IntentFilter(MEMES_NETWORK)
        registerReceiver(MemesReceiver,filterIntent)

        StartJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        CancelJob()
    }


    private fun StartJob() {
        var component = ComponentName(this,MemeScheduler::class.java)

        val jobInfo = JobInfo.Builder(jobID,component)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

        var memesJob = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        memesJob.schedule(jobInfo.build())
    }

    private fun CancelJob(){
        var memesJob = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        memesJob.cancel(jobID)
    }
}