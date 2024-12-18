package com.app.focustimer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * A composable to display information in a two-line format.
 * It shows a primary text and a secondary label.
 *
 * @param modifier Modifier to be applied to the Column.
 * @param text The main text to display (e.g., number of rounds or time).
 * @param label The secondary label (e.g., "rounds" or "time").
 */
@Composable
fun InformationItem(
    modifier: Modifier = Modifier,
    text: String,
    label: String
) {

    // A column to display text and its label stacked vertically.
    Column(
        modifier = modifier
    ) {
        // The main text (e.g., number of rounds or time).
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(), // Ensures the text spans the full width.
            style = MaterialTheme.typography.bodyLarge, // Applies the large body style.
            color = MaterialTheme.colorScheme.primary // Uses primary color from the theme.
        )
        // The secondary label (e.g., "rounds" or "time").
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(), // Ensures the label spans the full width.
            style = MaterialTheme.typography.bodyLarge, // Applies the large body style.
            color = MaterialTheme.colorScheme.secondary // Uses secondary color from the theme.
        )
    }

}

/**
 * A preview composable to visualize the InformationItem in isolation.
 * This preview shows how the InformationItem would look with a "35" count and "today round" label.
 */
@Preview(showBackground = true)
@Composable
fun InformationItemPreview() {
    FocusTimerTheme {
        InformationItem(
            text = "35",
            label = "today round"
        )
    }
}
