package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface StringDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addString(string: StringEntity)


    @Update
    suspend fun update(string: StringEntity)

    @Delete
    suspend fun deleteString(string: StringEntity)

    @Query("Select * from string_ent")
    suspend fun showAll(): List<StringEntity>
}