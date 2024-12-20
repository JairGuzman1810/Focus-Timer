package com.app.focustimer.data.repository

import com.app.focustimer.data.local.dao.TimerSessionDao
import com.app.focustimer.domain.model.TimerSessionModel
import com.app.focustimer.domain.model.toTimerSessionEntity
import com.app.focustimer.domain.repository.LocalStorageRepository
import javax.inject.Inject

/**
 * LocalStorageRepositoryImpl is the implementation of the LocalStorageRepository interface.
 *
 * It provides access to the local database (Room) for storing and retrieving timer session data.
 * This class uses constructor injection for its dependencies, facilitated by Dagger Hilt.
 *
 * @constructor Injects the TimerSessionDao dependency.
 */
class LocalStorageRepositoryImpl @Inject constructor(private val timerSessionDao: TimerSessionDao) :
    LocalStorageRepository {

    /**
     * Saves a timer session to the local database.
     *
     * @param timerSessionModel The TimerSessionModel to save.
     * @return True if the save operation was successful, false otherwise.
     * @throws Exception if an error occurs during the save operation.
     */
    override suspend fun saveTimerSession(timerSessionModel: TimerSessionModel): Boolean {
        try {
            // Inserts the TimerSessionModel into the database after converting it to a TimerSessionEntity.
            val result =
                timerSessionDao.insertTimerSession(timerSessionModel.toTimerSessionEntity())
            // Checks if the insertion was successful by verifying that the returned row ID is not -1.
            return result.toInt() != -1
        } catch (e: Exception) {
            // If an exception occurs, rethrow it to be handled by the caller.
            throw e
        }
    }

    /**
     * Retrieves a timer session from the local database based on the provided date.
     *
     * @param date The date of the timer session to retrieve.
     * @return The TimerSessionModel corresponding to the date.
     * @throws Exception if an error occurs during the retrieval operation.
     */
    override suspend fun getTimerSessionByDate(date: String): TimerSessionModel {
        try {
            // Initialize variables to store the total timer value and the number of rounds.
            var timerValue: Long = 0
            var rounds = 0
            // Retrieves all timer sessions for the given date.
            timerSessionDao.getTimerSessionByDate(date).map { session ->
                // Accumulates the timer value from each session.
                timerValue += session.value
                // Increments the round count for each session.
                rounds += 1
            }
            // Creates a new TimerSessionModel with the accumulated data.
            return TimerSessionModel(
                date = date,
                value = timerValue,
                round = rounds
            )
        } catch (e: Exception) {
            // If an exception occurs, rethrow it to be handled by the caller.
            throw e
        }
    }
}