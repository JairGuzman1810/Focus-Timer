package com.app.focustimer.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.R
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * A composable that displays an icon with a border and a clickable area.
 * It allows interaction through the `onTap` lambda function.
 *
 * @param modifier Modifier to be applied to the Icon composable.
 * @param icon The drawable resource ID for the icon to display.
 * @param onTap A function that is invoked when the icon is tapped.
 */
@Composable
fun BorderedIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onTap: () -> Unit = {}
) {
    // Renders the icon with a border and a clickable area.
    Icon(
        modifier = modifier
            .size(FocusTimerTheme.dimens.iconSizeNormal) // Sets the icon size.
            .border(
                width = FocusTimerTheme.dimens.borderNormal, // Border width.
                color = MaterialTheme.colorScheme.primary, // Border color.
                shape = CircleShape // Circular shape for the border.
            )
            .padding(FocusTimerTheme.dimens.paddingSmall) // Padding around the icon.
            .clickable { onTap() }, // Trigger the onTap function when clicked.
        imageVector = ImageVector.vectorResource(id = icon), // Loads the icon from resources.
        contentDescription = null, // No content description as this is purely for decoration.
        tint = MaterialTheme.colorScheme.primary // Icon tint color.
    )
}

/**
 * A preview of the BorderedIcon composable to visualize its behavior in isolation.
 * Displays a sample icon with a border and clickable functionality.
 */
@Preview(showBackground = true)
@Composable
fun BorderedIconPreview() {
    FocusTimerTheme {
        BorderedIcon(
            icon = R.drawable.ic_launcher_background // Sample icon for preview.
        )
    }
}
