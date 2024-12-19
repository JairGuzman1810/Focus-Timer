package com.app.focustimer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * FocusTimerApp serves as the entry point for the Focus Timer application.
 * This class is annotated with @HiltAndroidApp to enable Dagger Hilt's
 * dependency injection throughout the application.
 *
 * Hilt generates a base class that the application class extends, allowing
 * the injection of dependencies into Android components.
 */
@HiltAndroidApp
class FocusTimerApp: Application()  {
}