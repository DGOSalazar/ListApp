package com.example.listexercise.loadActivity.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listexercise.loadActivity.data.database.dao.Queries
import com.example.listexercise.loadActivity.data.database.entities.ListResultQuote

@Database(entities = [ListResultQuote::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getDao():Queries
}