package com.app.focustimer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * A composable for displaying an individual item in the timer type selection.
 * It displays a text label that can be clicked to select a timer type.
 *
 * @param modifier Modifier to be applied to the Box.
 * @param text The text to display for the timer type (e.g., "Focus", "Short Break", "Long Break").
 * @param textColor The color of the text (primary or secondary color depending on selection).
 * @param onTap The callback to trigger when the item is clicked, passing the selected timer type.
 */
@Composable
fun TimerTypeItem(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    onTap: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize() // Ensures the Box fills the available space.
            .padding(FocusTimerTheme.dimens.paddingSmall) // Adds padding around the item.
            .clickable { onTap() }, // Trigger the onTap callback when clicked.
        contentAlignment = Alignment.Center // Centers the text inside the Box.
    ) {
        // Displays the text for the timer type, with the specified color and style.
        Text(
            text,
            modifier = Modifier
                .fillMaxWidth() // Ensures the text spans the full width.
                .align(Alignment.Center), // Centers the text inside the Box.
            style = MaterialTheme.typography.bodyMedium, // Uses medium body text style.
            color = textColor // Uses the provided text color (primary or secondary).
        )
    }
}

/**
 * A preview composable to visualize the TimerTypeItem in isolation.
 * This preview shows how the TimerTypeItem would look with a "Focus" label.
 */
@Preview(showBackground = true)
@Composable
fun TimerTypeItemPreview() {
    FocusTimerTheme {
        TimerTypeItem(
            text = "Focus",
            textColor = MaterialTheme.colorScheme.primary
        )
    }
}