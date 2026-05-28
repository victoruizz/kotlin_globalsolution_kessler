package br.com.fiap.kessler.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.R
import br.com.fiap.kessler.data.model.RiskLevel
import br.com.fiap.kessler.ui.theme.RiskCritical
import br.com.fiap.kessler.ui.theme.RiskHigh
import br.com.fiap.kessler.ui.theme.RiskLow
import br.com.fiap.kessler.ui.theme.RiskMedium

fun riskColor(level: RiskLevel): Color = when (level) {
    RiskLevel.BAIXO -> RiskLow
    RiskLevel.MEDIO -> RiskMedium
    RiskLevel.ALTO -> RiskHigh
    RiskLevel.CRITICO -> RiskCritical
}

fun formatNumber(value: Double): String =
    if (value % 1.0 == 0.0) value.toLong().toString() else value.toString()

@Composable
fun RiskBadge(level: RiskLevel, modifier: Modifier = Modifier) {
    val color = riskColor(level)
    Surface(
        modifier = modifier,
        color = Color.Transparent,
        contentColor = color,
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, color)
    ) {
        Text(
            text = stringResource(R.string.risk_badge, level.label.uppercase()),
            style = MaterialTheme.typography.labelSmall,
            color = color,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
