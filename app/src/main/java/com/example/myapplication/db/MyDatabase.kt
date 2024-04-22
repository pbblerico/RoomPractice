package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [StringEntity::class], version = 2)
abstract class MyDatabase: RoomDatabase() {
    abstract fun stringDao(): StringDao
}