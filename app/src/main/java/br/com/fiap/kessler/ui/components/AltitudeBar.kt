package br.com.fiap.kessler.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.R
import br.com.fiap.kessler.ui.theme.MarsOrange
import br.com.fiap.kessler.ui.theme.SpaceBorder
import br.com.fiap.kessler.ui.theme.TextSecondary
import br.com.fiap.kessler.ui.theme.TextTertiary

@Composable
fun AltitudeBar(altitudeKm: Double, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ZoneLabel(text = stringResource(R.string.altitude_zone_leo), modifier = Modifier.weight(1f))
            ZoneLabel(text = stringResource(R.string.altitude_zone_meo), modifier = Modifier.weight(1f))
            ZoneLabel(text = stringResource(R.string.altitude_zone_geo), modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
        ) {
            val width = size.width
            val centerY = size.height / 2f
            val third = width / 3f
            val baseStroke = 2.dp.toPx()
            val tickStroke = 1.dp.toPx()

            drawLine(
                color = SpaceBorder,
                start = Offset(0f, centerY),
                end = Offset(width, centerY),
                strokeWidth = baseStroke
            )
            drawLine(
                color = SpaceBorder,
                start = Offset(third, centerY - 6.dp.toPx()),
                end = Offset(third, centerY + 6.dp.toPx()),
                strokeWidth = tickStroke
            )
            drawLine(
                color = SpaceBorder,
                start = Offset(third * 2f, centerY - 6.dp.toPx()),
                end = Offset(third * 2f, centerY + 6.dp.toPx()),
                strokeWidth = tickStroke
            )

            val fraction = altitudeFraction(altitudeKm)
            val markerX = (fraction * width).coerceIn(0f, width)
            drawLine(
                color = MarsOrange,
                start = Offset(markerX, centerY - 10.dp.toPx()),
                end = Offset(markerX, centerY + 10.dp.toPx()),
                strokeWidth = 2.dp.toPx()
            )
            drawCircle(
                color = MarsOrange,
                radius = 5.dp.toPx(),
                center = Offset(markerX, centerY)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.value_km, formatNumber(altitudeKm)),
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
    }
}

@Composable
private fun ZoneLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = TextTertiary,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

private fun altitudeFraction(altitudeKm: Double): Float {
    val third = 1f / 3f
    return when {
        altitudeKm <= 2000.0 -> {
            val raw = ((altitudeKm - 200.0).coerceIn(0.0, 1800.0) / 1800.0).toFloat()
            raw * third
        }
        altitudeKm < 35786.0 -> {
            val raw = ((altitudeKm - 2000.0) / (35786.0 - 2000.0)).toFloat()
            third + raw * third
        }
        else -> (2f / 3f) + third * 0.5f
    }
}
