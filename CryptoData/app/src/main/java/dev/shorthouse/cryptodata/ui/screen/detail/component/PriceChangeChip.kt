package dev.shorthouse.cryptodata.ui.screen.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.shorthouse.cryptodata.ui.component.PriceChangePercentage
import dev.shorthouse.cryptodata.ui.theme.NegativeRedBackground
import dev.shorthouse.cryptodata.ui.theme.PositiveGreenBackground

@Composable
fun PriceChangePercentageChip(
    priceChangePercentage: Double,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (priceChangePercentage >= 0) {
        PositiveGreenBackground
    } else {
        NegativeRedBackground
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        PriceChangePercentage(
            priceChangePercentage = priceChangePercentage
        )
    }
}
