package com.example.countrybriefer.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countrybriefer.model.Country

@Database(entities = [Country :: class], version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDAO

    companion object{
       @Volatile private var instance: CountryDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext ,CountryDatabase:: class.java,"countrydatabase"
        ).build()
    }

}