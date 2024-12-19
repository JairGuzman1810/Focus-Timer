package com.app.focustimer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.app.focustimer.presentation.home.HomeScreen
import com.app.focustimer.presentation.theme.FocusTimerTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity class, which serves as the entry point of the application.
 * Annotated with @AndroidEntryPoint to allow Dagger Hilt to provide dependencies
 * to this activity and any other Hilt-enabled components.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity to the FocusTimer UI.
        setContent {

            // Calculate the window size class based on the current device's screen size.
            val windowSize = calculateWindowSizeClass(this)

            // Apply the FocusTimerTheme, passing the window size to adjust UI layout.
            FocusTimerTheme(
                windowSize = windowSize.widthSizeClass
            ) {
                // A Surface composable that sets up the background color and layout size.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ensure that the surface fills the entire screen.
                    color = MaterialTheme.colorScheme.surface // Use the theme's surface color.
                ) {
                    // Call the HomeScreen composable
                    HomeScreen()
                }
            }
        }
    }
}
