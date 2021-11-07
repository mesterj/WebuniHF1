package com.kite.joco.webunihf1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class OutgoingCallReceiver : BroadcastReceiver (){
    val LOGTAG = "OUTCREC"
    override fun onReceive(p0: Context, p1: Intent) {
      Log.i(LOGTAG,"Outgoing call receiver started")
      val calllognumber = p1.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        if (calllognumber != null) {
            savelog(calllognumber,p0)
        }
    }

    fun savelog(number : String,context: Context) {
        var stamp = Calendar.getInstance().time.toString()
        var callLog = CallLog(0L,number,stamp)
        Log.i(LOGTAG,"Timestamp saved: $stamp")

        val insertThread = Thread {AppDatabase.getInstance(context).callDao().insertLogs(callLog)}
        insertThread.start()
    }

}
/*
Thread {
AppDatabase.getInstance(this@MainActivity).gradeDao().insertGrades(grade)
}
dbThread.start()
 */