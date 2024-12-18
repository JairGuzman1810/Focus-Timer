package com.app.focustimer.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.presentation.theme.FocusTimerTheme

/**
 * A customizable button composable used for starting or resetting the timer.
 * This button allows for custom text, text color, background color, and an action to be performed when clicked.
 *
 * @param modifier Modifier to be applied to the Button composable, allowing for customization of size, alignment, etc.
 * @param text The text displayed on the button.
 * @param textColor The color of the text displayed on the button.
 * @param buttonColor The background color of the button.
 * @param onTap The action to be performed when the button is clicked.
 */
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    buttonColor: Color,
    onTap: () -> Unit = {}
) {

    // Button composable that is customized with color, text, and an onTap action.
    Button(
        modifier = modifier
            .height(FocusTimerTheme.dimens.buttonHeightNormal), // Sets the button's height.
        shape = RoundedCornerShape(FocusTimerTheme.dimens.roundedShapeNormal), // Gives the button rounded corners.
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor // Sets the background color of the button.
        ),
        onClick = { onTap() } // Executes the onTap action when the button is clicked.
    ) {
        // Text displayed on the button.
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically), // Ensures text is centered within the button.
            style = MaterialTheme.typography.bodyLarge, // Applies the bodyLarge text style.
            color = textColor // Sets the color of the text.
        )
    }
}

/**
 * A preview composable to visualize the CustomButton in isolation.
 * This preview shows a "Start" button with the text color and button color applied.
 */
@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    FocusTimerTheme {
        CustomButton(
            text = "Start", // Displays "Start" on the button.
            textColor = MaterialTheme.colorScheme.surface, // Sets the text color.
            buttonColor = MaterialTheme.colorScheme.primary // Sets the background color.
        )
    }
}