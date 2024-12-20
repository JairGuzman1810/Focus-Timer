package com.app.focustimer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.focustimer.data.local.dao.TimerSessionDao
import com.app.focustimer.data.local.entity.TimerSessionEntity

/**
 * AppDatabase is the Room database for the Focus Timer application.
 *
 * It defines the entities and version of the database.
 */
@Database(entities = [TimerSessionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the TimerSessionDao for interacting with the timer session table.
     *
     * @return The TimerSessionDao instance.
     */
    abstract fun timerSessionDao(): TimerSessionDao
}