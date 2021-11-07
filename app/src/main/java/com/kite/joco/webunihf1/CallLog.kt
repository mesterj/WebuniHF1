package com.kite.joco.webunihf1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.Date


@Entity(tableName = "calllog")
data class CallLog (
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name="phonenum") var phoneNum: String,
    @ColumnInfo(name="timestamp") var timeStamp: String
        ){
}