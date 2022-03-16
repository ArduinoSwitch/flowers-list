package com.setesh.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.setesh.data.database.photo.PhotoDao
import com.setesh.data.database.photo.PhotoEntity

private const val APP_DB_NAME = "app_database"

@Database(
    entities = [PhotoEntity::class],
    version = 1
)

//@TypeConverters(Converters::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context.applicationContext, AppDatabase::class.java, APP_DB_NAME)
            .build()
    }
}
