package com.example.myapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="string_ent")
data class StringEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "str") val str: String

)