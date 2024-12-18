package com.app.focustimer.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * A composable that dynamically resizes text to fit within the available width.
 * If the text overflows, its font size is adjusted until it fits the container.
 *
 * @param modifier Modifier to be applied to the Text composable.
 * @param text The string content to be displayed.
 * @param textStyle The style to be applied to the text (default: MaterialTheme.typography.displayLarge).
 */
@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.displayLarge
) {
    // Keeps track of the current text style and allows dynamic resizing.
    var timeTextStyle by remember { mutableStateOf(textStyle) }

    // Factor by which the font size is reduced when the text overflows.
    val fontSizeFactor = 0.95

    // Displays the text and dynamically adjusts its size if it overflows.
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(), // Ensures the text spans the full width of its container.
        softWrap = false, // Prevents the text from wrapping to the next line.
        style = timeTextStyle, // Applies the current text style.
        onTextLayout = { result ->
            // Checks if the text overflows its container.
            if (result.didOverflowWidth) {
                // Reduces the font size and updates the text style to fit within the container.
                timeTextStyle = timeTextStyle.copy(
                    fontSize = timeTextStyle.fontSize * fontSizeFactor
                )
            }
        }
    )
}

/**
 * A preview of the AutoResizedText composable to visualize its behavior in isolation.
 * Displays a sample text using the default theme and text style.
 */
@Preview(showBackground = true)
@Composable
fun AutoResizedTextPreview() {
    FocusTimerTheme {
        AutoResizedText(
            text = "Focus timer"
        )
    }
}
