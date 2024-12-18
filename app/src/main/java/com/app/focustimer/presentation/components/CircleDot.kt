package com.app.focustimer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * A composable that renders a circle dot for decorative purposes.
 * It is used to display a simple circular dot with a specified color.
 *
 * @param modifier Modifier to be applied to the Box composable, allowing for customization of size, alignment, etc.
 * @param color The color of the circle. Default is the primary color from the theme.
 */
@Composable
fun CircleDot(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    // Box that shapes and styles the circle.
    Box(
        modifier = modifier
            .size(FocusTimerTheme.dimens.iconSizeNormal) // Defines the size of the dot.
            .clip(CircleShape) // Clips the Box to a circular shape.
            .background(color) // Applies the specified color to the circle.
    )
}

/**
 * A preview composable to display the CircleDot in isolation, useful for visualization in the design tool.
 * The circle dot uses the primary color of the theme for its background.
 */
@Preview(showBackground = true)
@Composable
fun CircleDotPreview() {
    FocusTimerTheme {
        CircleDot() // Displays a default CircleDot using the primary color.
    }
}
