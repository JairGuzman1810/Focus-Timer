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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.focustimer.R
import com.app.focustimer.presentation.components.AutoResizedText
import com.app.focustimer.presentation.components.BorderedIcon
import com.app.focustimer.presentation.components.CircleDot
import com.app.focustimer.presentation.components.CustomButton
import com.app.focustimer.presentation.components.InformationItem
import com.app.focustimer.presentation.components.TimerTypeItem
import com.app.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(FocusTimerTheme.dimens.paddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier.size(FocusTimerTheme.dimens.iconSizeNormal),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        AutoResizedText(
            text = "Focus Timer",
            textStyle = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        Row {
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary)
        }

        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        TimerSession(
            timer = "05:00",
            onIncreaseTap = {
                // TODO: Implement logic.
            },
            onDecreaseTap = {
                // TODO: Implement logic.
            }
        )

        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        TimerTypeSession(
            onTap = {
                // TODO: Implement logic.
            }
        )

        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            CustomButton(
                text = "Start",
                textColor = MaterialTheme.colorScheme.surface,
                buttonColor = MaterialTheme.colorScheme.primary,
                onTap = {
                    // TODO: Implement logic.
                }
            )

            CustomButton(
                text = "Reset",
                textColor = MaterialTheme.colorScheme.primary,
                buttonColor = MaterialTheme.colorScheme.surface,
                onTap = {
                    // TODO: Implement logic.
                }
            )

        }

        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))

        InformationSession(
            modifier = Modifier.weight(1f),
            round = "10",
            time = "45:00"
        )


    }
}

@Composable
fun TimerSession(
    modifier: Modifier = Modifier,
    timer: String,
    onIncreaseTap: () -> Unit = {},
    onDecreaseTap: () -> Unit = {},
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_minus, onTap = onDecreaseTap)
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }

        AutoResizedText(
            text = timer,
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .align(Alignment.CenterVertically),
            textStyle = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_plus, onTap = onIncreaseTap)
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
    }

}

@Composable
fun TimerTypeSession(
    modifier: Modifier = Modifier,
    onTap: () -> Unit = {}
) {
    val gridCount = 3
    val itemSpacing = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal)

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(FocusTimerTheme.dimens.spacerLarge),
        columns = GridCells.Fixed(gridCount),
        horizontalArrangement = itemSpacing,
        verticalArrangement = itemSpacing,
    ) {
        item(
            key = "FT"
        ) {
            TimerTypeItem(text = "Focus Timer", textColor = MaterialTheme.colorScheme.primary)
        }

        item(
            key = "SB"
        ) {
            TimerTypeItem(text = "Short Break", textColor = MaterialTheme.colorScheme.secondary)
        }

        item(
            key = "LB"
        ) {
            TimerTypeItem(text = "Long Break", textColor = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
fun InformationSession(
    modifier: Modifier = Modifier,
    round: String,
    time: String,
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {

        Row(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {

            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = round,
                label = "rounds"
            )

            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = time,
                label = "time"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FocusTimerTheme {
        HomeScreen()
    }
}