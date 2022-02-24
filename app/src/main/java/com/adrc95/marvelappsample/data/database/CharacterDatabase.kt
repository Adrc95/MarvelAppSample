package com.adrc95.marvelappsample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        private const val DATABASE_NAME = "character_database"

        fun build(applicationContext: Context) = Room.databaseBuilder(
            applicationContext,
            CharacterDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}