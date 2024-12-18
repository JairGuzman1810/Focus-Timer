package com.app.focustimer.core

/**
 * Constants used throughout the Focus Timer application.
 * These values define the durations for focus time, short break, and long break.
 * They also provide utility constants for time conversion calculations.
 */
class Constants {

    companion object {
        // Duration for focus time in minutes
        const val FOCUS_TIME: Long = 25

        // Duration for short break time in minutes
        const val SHORT_BREAK_TIME: Long = 5

        // Duration for long break time in minutes
        const val LONG_BREAK_TIME: Long = 15

        // Time unit: one second in milliseconds
        const val ONE_SEC_IN_MILLIS: Long = 1000

        // Time unit: one minute in milliseconds
        const val ONE_MIN_IN_MILLIS: Long = 60000

        // Time unit: one minute in seconds
        const val ONE_MIN_IN_SEC: Int = 60

        // Time unit: one hour in minutes
        const val ONE_HOUR_IN_MIN: Int = 60
    }
}