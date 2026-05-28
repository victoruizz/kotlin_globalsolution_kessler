package br.com.fiap.kessler.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.ui.theme.MarsOrange
import br.com.fiap.kessler.ui.theme.MarsOrangeDim
import br.com.fiap.kessler.ui.theme.TextPrimary
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OrbitalDecoration(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(240.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val strokeWidth = 1.dp.toPx()
        val base = size.minDimension
        val outerRadius = base * 0.45f
        val middleRadius = base * 0.32f
        val innerRadius = base * 0.19f

        withTransform({
            rotate(degrees = -20f, pivot = center)
            scale(scaleX = 1f, scaleY = 0.42f, pivot = center)
        }) {
            drawCircle(
                color = MarsOrangeDim.copy(alpha = 0.3f),
                radius = outerRadius,
                center = center,
                style = Stroke(width = strokeWidth)
            )
            drawCircle(
                color = MarsOrangeDim.copy(alpha = 0.5f),
                radius = middleRadius,
                center = center,
                style = Stroke(width = strokeWidth)
            )
            drawCircle(
                color = MarsOrange.copy(alpha = 0.7f),
                radius = innerRadius,
                center = center,
                style = Stroke(width = strokeWidth)
            )

            val markerAngle = Math.toRadians(60.0)
            val markerCenter = Offset(
                x = center.x + middleRadius * cos(markerAngle).toFloat(),
                y = center.y + middleRadius * sin(markerAngle).toFloat()
            )
            drawCircle(
                color = MarsOrange,
                radius = 4.dp.toPx(),
                center = markerCenter
            )
        }

        drawCircle(
            color = TextPrimary,
            radius = 6.dp.toPx(),
            center = center
        )
    }
}
