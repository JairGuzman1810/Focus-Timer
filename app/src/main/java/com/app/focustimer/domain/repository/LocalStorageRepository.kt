package com.app.focustimer.domain.repository

import com.app.focustimer.domain.model.TimerSessionModel

/**
 * LocalStorageRepository defines the contract for interacting with local storage for timer session data.
 *
 * This interface provides methods for saving and retrieving timer session records.
 */
interface LocalStorageRepository {

    /**
     * Saves a timer session to local storage.
     *
     * @param timerSessionModel The TimerSessionModel to save.
     * @return True if the save operation was successful, false otherwise.
     */
    suspend fun saveTimerSession(timerSessionModel: TimerSessionModel): Boolean

    /**
     * Retrieves a timer session from local storage based on the provided date.
     *
     * @param date The date of the timer session to retrieve.
     * @return The TimerSessionModel corresponding to the date.
     */
    suspend fun getTimerSessionByDate(date: String): TimerSessionModel
}