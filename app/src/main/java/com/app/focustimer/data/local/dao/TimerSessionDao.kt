package com.app.focustimer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.focustimer.data.local.entity.TimerSessionEntity

/**
 * TimerSessionDao provides database operations for the TimerSessionEntity.
 *
 * This interface defines methods for retrieving and inserting timer session records.
 */
@Dao
interface TimerSessionDao {

    /**
     * Retrieves a list of timer sessions that have a date containing the provided date string.
     *
     * @param date The date string to search for (e.g., "2024-01-26").
     * @return A mutable list of TimerSessionEntity objects matching the date.
     */
    @Query("SELECT * FROM timer_session WHERE date LIKE '%' || :date || '%'")
    suspend fun getTimerSessionByDate(date: String): MutableList<TimerSessionEntity>

    /**
     * Inserts a new timer session record into the database.
     *
     * If a conflict occurs (e.g., a record with the same ID already exists),
     * the existing record will be replaced.
     *
     * @param timerSessionEntity The TimerSessionEntity to insert.
     * @return The row ID of the newly inserted record.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimerSession(timerSessionEntity: TimerSessionEntity): Long
}