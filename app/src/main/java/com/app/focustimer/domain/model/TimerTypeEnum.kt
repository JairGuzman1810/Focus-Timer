
package com.app.focustimer.domain.model

import com.app.focustimer.core.Constants.Companion.FOCUS_TIME
import com.app.focustimer.core.Constants.Companion.LONG_BREAK_TIME
import com.app.focustimer.core.Constants.Companion.ONE_MIN_IN_SEC
import com.app.focustimer.core.Constants.Companion.ONE_SEC_IN_MILLIS
import com.app.focustimer.core.Constants.Companion.SHORT_BREAK_TIME

/**
 * Represents the types of timers available in the Focus Timer application.
 * Each timer type has a title and an associated duration in minutes.
 * Provides a utility function to convert the time from minutes to milliseconds.
 */
enum class TimerTypeEnum(val title: String, private val time: Long) {

    // Focus timer type with a duration set in FOCUS_TIME
    FOCUS("Focus Time", FOCUS_TIME),

    // Short break timer type with a duration set in SHORT_BREAK_TIME
    SHORT_BREAK("Short Break", SHORT_BREAK_TIME),

    // Long break timer type with a duration set in LONG_BREAK_TIME
    LONG_BREAK("Long Break", LONG_BREAK_TIME);

    /**
     * Converts the timer duration from minutes to milliseconds.
     * This is useful for setting up countdowns in the app.
     *
     * @return The time in milliseconds
     */
    fun timeToMillis(): Long {
        return time * ONE_MIN_IN_SEC * ONE_SEC_IN_MILLIS
    }
}