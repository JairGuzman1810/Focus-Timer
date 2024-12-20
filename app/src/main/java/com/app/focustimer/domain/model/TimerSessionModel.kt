package com.app.focustimer.domain.model

import com.app.focustimer.data.local.entity.TimerSessionEntity

/**
 * TimerSessionModel represents a timer session with its date, total value, and number of rounds.
 *
 * This data class is used to transfer timer session data between the data layer and the domain/presentation layers.
 *
 * @property date The date of the timer session.
 * @property value The total duration of the timer session in milliseconds.
 * @property round The number of rounds completed in the session (optional).
 */
data class TimerSessionModel(
    /**
     * The date of the timer session.
     */
    var date: String,

    /**
     * The total duration of the timer session in milliseconds.
     */
    var value: Long,

    /**
     * The number of rounds completed in the session (optional).
     * Defaults to 0 if not provided.
     */
    var round: Int? = 0
)

/**
 * Extension function to convert a TimerSessionModel object to a TimerSessionEntity object.
 *
 * This function is used to map data from the domain layer to the data layer (Room database).
 *
 * @return A TimerSessionEntity object with the same data as the TimerSessionModel.
 */
fun TimerSessionModel.toTimerSessionEntity(): TimerSessionEntity {
    // Creates a new TimerSessionEntity with the data from the TimerSessionModel.
    return TimerSessionEntity(
        date = this.date,
        value = this.value,
    )
}
