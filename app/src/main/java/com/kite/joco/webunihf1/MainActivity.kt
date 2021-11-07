package com.kite.joco.webunihf1

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import com.kite.joco.webunihf1.databinding.ActivityMainBinding
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    var LOGTAG = "CALLLOG.MAIN"

    private lateinit var adapter: CallAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        Log.i(LOGTAG,"Appendlog started")
        receiverRigthsWithPermissionCheck()
        initRecyclerView()
    }

    @NeedsPermission(Manifest.permission.PROCESS_OUTGOING_CALLS)
    fun receiverRigths(){
        Toast.makeText(this,"Receiver allowed",Toast.LENGTH_LONG)
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    fun callNumber(numberString: String) {
        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+numberString))
        startActivity(intentCall)
    }

    private fun initRecyclerView() {
        Thread {
        var calllogList = AppDatabase.getInstance(this@MainActivity).callDao().getAllCalls()
            runOnUiThread {
                for (calllog in calllogList) {
                    Log.i(LOGTAG,"elements in init ${calllog.phoneNum}")
                }
                adapter = CallAdapter(this, calllogList)
                mainBinding.recyclerCall.adapter = adapter
            }
        }.start()
    }
}
