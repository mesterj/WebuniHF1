package com.kite.joco.webunihf1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CallLogDao {

    @Query("SELECT * FROM calllog")
    fun getAllCalls(): List<CallLog>

    @Query("SELECT * FROM calllog WHERE CallLog.id = :callid")
    fun getSpecificLogs(callid: Long): CallLog

    @Insert
    fun insertLogs(vararg newlog: CallLog)

    @Delete
    fun deleteLog(calllog: CallLog)
}
