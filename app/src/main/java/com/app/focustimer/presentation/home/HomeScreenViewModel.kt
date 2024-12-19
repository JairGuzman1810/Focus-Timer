/**
 * ViewModel for the Home Screen of the Focus Timer application.
 * Manages timer functionality, tracks rounds, and updates the UI states.
 * Contains methods to handle timer operations and format time values.
 */
package com.app.focustimer.presentation.home

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.focustimer.core.Constants.Companion.ONE_HOUR_IN_MIN
import com.app.focustimer.core.Constants.Companion.ONE_MIN_IN_MILLIS
import com.app.focustimer.core.Constants.Companion.ONE_MIN_IN_SEC
import com.app.focustimer.core.Constants.Companion.ONE_SEC_IN_MILLIS
import com.app.focustimer.domain.model.TimerTypeEnum
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeScreenViewModel is the ViewModel for the Home Screen of the Focus Timer application.
 * It manages the application's timer functionality, tracks rounds, and updates UI states.
 * This class uses constructor injection for its dependencies, facilitated by Dagger Hilt.
 *
 * @constructor Injects required dependencies, such as the timer, into the ViewModel.
 */
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    // Timer object for managing the countdown
    private lateinit var timer: CountDownTimer

    // Tracks whether the timer is currently active
    private var isTimerActive: Boolean = false

    // State for the current timer value in milliseconds
    private val _timerValue = mutableLongStateOf(TimerTypeEnum.FOCUS.timeToMillis())
    val timerValueState = _timerValue // Public state to observe

    // State for the current timer type (Focus, Short Break, Long Break)
    private val _timerTypeState = mutableStateOf(TimerTypeEnum.FOCUS)
    val timerTypeState = _timerTypeState // Public state to observe

    // State to track the number of completed rounds
    private val _roundsState = mutableIntStateOf(0)
    val roundsState = _roundsState // Public state to observe

    // State to track the total time spent today in milliseconds
    private val _todayTimeState = mutableLongStateOf(0)
    val todayTimeState = _todayTimeState // Public state to observe

    /**
     * Starts the timer with the current timer type and duration.
     * Updates the timer value and today's time as the timer ticks.
     * Increments the round count when the timer starts.
     */
    fun onStartTimer() {
        // Launches a coroutine in the ViewModel's scope to ensure that this runs on a background thread
        viewModelScope.launch {
            // Initializes the CountDownTimer with the current timer value and tick interval
            timer = object : CountDownTimer(
                _timerValue.longValue, // Total duration of the timer in milliseconds
                ONE_SEC_IN_MILLIS // Tick interval, updating every second
            ) {
                /**
                 * Called on every tick of the timer (every second in this case).
                 * Updates the remaining timer value and increments the total time tracked for today.
                 *
                 * @param millisUntilFinished The time left in the countdown in milliseconds.
                 */
                override fun onTick(millisUntilFinished: Long) {
                    // Updates the current timer value with the remaining time
                    _timerValue.longValue = millisUntilFinished
                    // Increments the total time spent today by one second
                    _todayTimeState.value += ONE_SEC_IN_MILLIS
                }

                /**
                 * Called when the timer finishes (reaches 0).
                 * Cancels the timer and resets its state.
                 */
                override fun onFinish() {
                    onCancelTimer() // Resets the timer state when it ends
                }
            }

            // Starts the CountDownTimer and sets up initial state
            timer.start().also {
                // Checks if the timer was previously inactive
                if (!isTimerActive) {
                    // Increments the round count if starting a new timer session
                    _roundsState.value += 1
                }
                // Marks the timer as active
                isTimerActive = true
            }
        }
    }


    /**
     * Cancels the timer if it is running.
     * Optionally resets the timer to the current timer type's default duration.
     *
     * @param reset Whether to reset the timer value to the default duration.
     */
    fun onCancelTimer(reset: Boolean = false) {
        // Attempts to cancel the timer if it has been initialized
        try {
            timer.cancel() // Stops the timer if it is currently running
        } catch (_: UninitializedPropertyAccessException) {
            // Handles cases where the timer has not been initialized
            // No further action is required in this case
        }

        // Resets the timer value if:
        // - The timer is not active (already stopped), or
        // - The `reset` parameter is true
        if (!isTimerActive || reset) {
            // Sets the timer value to the default duration for the current timer type
            _timerValue.longValue = _timerTypeState.value.timeToMillis()
        }

        // Updates the state to mark the timer as inactive
        isTimerActive = false
    }

    /**
     * Restarts the timer with the current duration if it is active.
     */
    private fun onResetTime() {
        // Checks if the timer is active before attempting to reset
        if (isTimerActive) {
            onCancelTimer() // Cancels the current timer to stop ongoing countdown
            onStartTimer()  // Starts the timer again with the updated duration
        }
    }

    /**
     * Updates the timer type and resets the timer to the new type's default duration.
     *
     * @param timerType The new timer type to set.
     */
    fun onUpdateType(timerType: TimerTypeEnum) {
        // Updates the current timer type to the provided value
        _timerTypeState.value = timerType

        // Cancels the current timer and resets it to the default duration of the new type
        onCancelTimer(reset = true)
    }

    /**
     * Increases the timer duration by one minute and restarts the timer if it is active.
     * Ensures the updated timer duration is applied immediately.
     */
    fun onIncreaseTime() {
        // Adds one minute (in milliseconds) to the current timer value
        _timerValue.value += ONE_MIN_IN_MILLIS

        // Restarts the timer to reflect the updated duration if it is currently active
        onResetTime()
    }

    /**
     * Decreases the timer duration by one minute and restarts the timer if it is active.
     * If the timer value becomes negative after the decrease, the timer is canceled.
     */
    fun onDecreaseTime() {
        // Subtracts one minute (in milliseconds) from the current timer value
        _timerValue.value -= ONE_MIN_IN_MILLIS

        // Restarts the timer with the updated duration if it is currently active
        onResetTime()

        // If the timer value becomes negative, cancels the timer to avoid invalid behavior
        if (_timerValue.longValue < 0) {
            onCancelTimer() // Cancels the current timer to stop ongoing countdown
        }
    }


    /**
     * Converts a time value in milliseconds to a "MM:SS" formatted string.
     *
     * @param value Time in milliseconds to be formatted.
     * @return A string representing the time in "MM:SS" format.
     */
    @SuppressLint("DefaultLocale")
    fun millisToMinutes(value: Long): String {
        // Convert the time value from milliseconds to total seconds
        val totalSeconds = value / ONE_SEC_IN_MILLIS

        // Calculate the number of full minutes
        val minutes = (totalSeconds / ONE_MIN_IN_SEC).toInt()

        // Calculate the remaining seconds after removing full minutes
        val seconds = (totalSeconds % ONE_MIN_IN_SEC).toInt()

        // Return the time as a formatted string "MM:SS"
        return String.format("%02d:%02d", minutes, seconds)
    }

    /**
     * Converts a time value in milliseconds to a readable string.
     * Formats the time in "HHh MMm" if the total duration is more than an hour,
     * otherwise in "MMm SSs" format.
     *
     * @param value Time in milliseconds to be formatted.
     * @return A string representing the time in either "HHh MMm" or "MMm SSs" format.
     */
    @SuppressLint("DefaultLocale")
    fun millisToHours(value: Long): String {
        // Convert the time value from milliseconds to total seconds
        val totalSeconds = value / ONE_SEC_IN_MILLIS

        // Calculate the remaining seconds after converting to minutes
        val seconds = (totalSeconds % ONE_MIN_IN_SEC)

        // Convert the total seconds to total minutes
        val totalMinutes = (totalSeconds / ONE_MIN_IN_SEC).toInt()

        // Calculate the number of full hours
        val hours = (totalMinutes / ONE_HOUR_IN_MIN)

        // Calculate the remaining minutes after removing full hours
        val minutes = (totalMinutes % ONE_HOUR_IN_MIN)

        // If the total minutes are less than or equal to one hour, return "MMm SSs"
        return if (totalMinutes <= ONE_HOUR_IN_MIN) {
            String.format("%02dm %02ds", minutes, seconds)
        }
        // Otherwise, return the time in "HHh MMm" format
        else {
            String.format("%02dh %02dm", hours, minutes)
        }
    }
}
