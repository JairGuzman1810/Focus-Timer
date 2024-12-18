package com.app.focustimer.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.R
import com.app.focustimer.domain.model.TimerTypeEnum
import com.app.focustimer.presentation.components.AutoResizedText
import com.app.focustimer.presentation.components.BorderedIcon
import com.app.focustimer.presentation.components.CircleDot
import com.app.focustimer.presentation.components.CustomButton
import com.app.focustimer.presentation.components.InformationItem
import com.app.focustimer.presentation.components.TimerTypeItem
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * HomeScreen is the main screen of the Focus Timer app.
 * It manages the timer display, session information, and user actions such as start, reset, and type selection.
 *
 * @param viewModel The ViewModel instance used to manage the UI state of the HomeScreen.
 */
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = HomeScreenViewModel()) {

    // State variables to hold the timer values, session type, and round information
    val timeState by remember { mutableStateOf(viewModel.timerValueState) }
    val timerTypeState by remember { mutableStateOf(viewModel.timerTypeState) }
    val roundsState by remember { mutableStateOf(viewModel.roundsState) }
    val todayTimeState by remember { mutableStateOf(viewModel.todayTimeState) }

    // Main container for the screen content, arranged in a vertical scrollable layout
    Column(
        modifier = Modifier
            .fillMaxSize() // Ensures the column takes up the full screen size
            .padding(FocusTimerTheme.dimens.paddingMedium) // Adds padding to the content
            .verticalScroll(rememberScrollState()), // Makes the column scrollable
        horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally
    ) {

        // Menu icon positioned at the top right corner of the screen
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier.size(FocusTimerTheme.dimens.iconSizeNormal),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu", // Accessible description for screen readers
                tint = MaterialTheme.colorScheme.primary // Icon color matches primary theme color
            )
        }

        // Title of the screen, auto-resized to fit available space
        AutoResizedText(
            text = "Focus Timer",
            textStyle = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.primary, // Title color
                textAlign = TextAlign.Center // Center-aligns the title text
            )
        )

        // Spacer to create vertical spacing between components
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        // Row of circle dots representing progress or stages
        Row {
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary) // Highlighted circle
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary) // Highlighted circle
        }

        // Spacer to separate the row of dots from the timer session
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        // Timer session component that allows increasing and decreasing the time
        TimerSession(
            timer = viewModel.millisToMinutes(timeState.longValue), // Converts time in milliseconds to minutes
            onIncreaseTap = {
                viewModel.onIncreaseTime() // Increase the timer when tapped
            },
            onDecreaseTap = {
                viewModel.onDecreaseTime() // Decrease the timer when tapped
            }
        )

        // Spacer between the timer session and the timer type selection
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        // Timer type selection session that allows the user to pick the type of timer
        TimerTypeSession(
            type = timerTypeState.value, // Current timer type
            onTap = { type ->
                viewModel.onUpdateType(type) // Update the timer type when tapped
            }
        )

        // Spacer between the timer type session and the start/reset buttons
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        // Column containing the "Start" and "Reset" buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            // Start button to begin the timer session
            CustomButton(
                text = "Start",
                textColor = MaterialTheme.colorScheme.surface, // Text color for the button
                buttonColor = MaterialTheme.colorScheme.primary, // Button background color
                onTap = {
                    viewModel.onStartTimer() // Start the timer when tapped
                }
            )

            // Reset button to cancel or reset the timer session
            CustomButton(
                text = "Reset",
                textColor = MaterialTheme.colorScheme.primary, // Text color for the button
                buttonColor = MaterialTheme.colorScheme.surface, // Button background color
                onTap = {
                    viewModel.onCancelTimer(true) // Cancel or reset the timer when tapped
                }
            )
        }

        // Spacer between the button row and the session information
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        // Information session displaying round and time data
        InformationSession(
            modifier = Modifier.weight(1f), // Takes up the remaining space
            round = roundsState.intValue.toString(), // Displays the current round
            time = viewModel.millisToHours(todayTimeState.longValue) // Displays total time spent today

        )
    }
}

/**
 * TimerSession displays the current timer value with buttons to increase or decrease the time.
 *
 * @param modifier Modifier to be applied to the TimerSession composable.
 * @param timer The current timer value, formatted as a string.
 * @param onIncreaseTap A function that is invoked when the increase button is tapped.
 * @param onDecreaseTap A function that is invoked when the decrease button is tapped.
 */
@Composable
fun TimerSession(
    modifier: Modifier = Modifier,
    timer: String,
    onIncreaseTap: () -> Unit = {},
    onDecreaseTap: () -> Unit = {},
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, // Vertically centers the row content
        horizontalArrangement = Arrangement.Center, // Centers the row content horizontally
    ) {

        // Column to display the decrease timer button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_minus, onTap = onDecreaseTap) // Minus icon to decrease time
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium)) // Spacer between icon and text
        }

        // Displays the current timer value
        AutoResizedText(
            text = timer,
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f) // The timer text takes up more space
                .align(Alignment.CenterVertically),
            textStyle = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.primary, // Text color for the timer
                textAlign = TextAlign.Center // Center-aligns the timer text
            )
        )

        // Column to display the increase timer button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_plus, onTap = onIncreaseTap) // Plus icon to increase time
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium)) // Spacer between icon and text
        }
    }
}

/**
 * TimerTypeSession displays a selection of timer types (e.g., Focus, Break) with clickable options.
 *
 * @param modifier Modifier to be applied to the TimerTypeSession composable.
 * @param type The current timer type to be displayed.
 * @param onTap A function that is invoked when a timer type is selected.
 */
@Composable
fun TimerTypeSession(
    modifier: Modifier = Modifier,
    type: TimerTypeEnum,
    onTap: (TimerTypeEnum) -> Unit = {}
) {
    val gridCount = 3 // Number of columns in the grid
    val itemSpacing = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal) // Spacing between items in the grid

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(FocusTimerTheme.dimens.spacerLarge), // Height of the grid
        columns = GridCells.Fixed(gridCount), // Define grid with fixed columns
        horizontalArrangement = itemSpacing, // Set horizontal spacing between items
        verticalArrangement = itemSpacing, // Set vertical spacing between items
    ) {
        items(
            TimerTypeEnum.entries.toTypedArray(),
        ) {
            TimerTypeItem(
                text = it.title, // Displays the title of the timer type
                textColor = if (type == it)
                    MaterialTheme.colorScheme.primary // Highlight the selected timer type
                else
                    MaterialTheme.colorScheme.secondary, // Default color for unselected timer types
                onTap = {onTap(it)} // Handle the tap event for selecting a timer type
            )
        }
    }
}

/**
 * InformationSession displays session data such as the current round and total time spent.
 * It arranges the data in a row, with each piece of information occupying equal space.
 *
 * @param modifier Modifier to be applied to the InformationSession composable.
 * @param round The current round, displayed as a string.
 * @param time The total time spent today, displayed as a string.
 */
@Composable
fun InformationSession(
    modifier: Modifier = Modifier,
    round: String,
    time: String,
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter, // Aligns content to the bottom center
    ) {

        // Row to display the round and time information side by side
        Row(
            modifier = Modifier.align(Alignment.BottomCenter) // Aligns the row at the bottom center of the Box
        ) {

            // InformationItem displaying the round value
            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f), // Each item takes up equal space
                text = round, // Round value to be displayed
                label = "rounds" // Label for the round information
            )

            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f) // Adds space between the two InformationItems
            )

            // InformationItem displaying the total time value
            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f), // Each item takes up equal space
                text = time, // Time value to be displayed
                label = "time" // Label for the time information
            )
        }
    }
}


/**
 * A preview of the HomeScreen composable, which allows you to visualize its layout and behavior.
 * This preview will show the HomeScreen within the context of the FocusTimerTheme, providing a full view of the UI.
 */
@Preview(showBackground = true) // Ensures that the preview is rendered with a background for context.
@Composable
fun HomeScreenPreview() {
    FocusTimerTheme { // Applies the FocusTimerTheme to the HomeScreen for consistent styling.
        HomeScreen() // Renders the HomeScreen composable for the preview.
    }
}
