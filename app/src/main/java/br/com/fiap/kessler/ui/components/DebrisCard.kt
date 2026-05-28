package br.com.fiap.kessler.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.R
import br.com.fiap.kessler.data.model.Debris
import br.com.fiap.kessler.ui.theme.SpaceBorder
import br.com.fiap.kessler.ui.theme.SpaceSurfaceElevated
import br.com.fiap.kessler.ui.theme.TextPrimary
import br.com.fiap.kessler.ui.theme.TextSecondary
import br.com.fiap.kessler.ui.theme.TextTertiary

@Composable
fun DebrisCard(debris: Debris, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = SpaceSurfaceElevated),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, SpaceBorder)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = debris.id,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextTertiary,
                    modifier = Modifier.weight(1f)
                )
                RiskBadge(level = debris.riskLevel)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = debris.name,
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.country_year, debris.countryOfOrigin, debris.launchYear),
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 1.dp, color = SpaceBorder)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                MiniMetric(
                    label = stringResource(R.string.card_metric_altitude),
                    value = stringResource(R.string.value_km, formatNumber(debris.altitudeKm)),
                    modifier = Modifier.weight(1f)
                )
                MiniMetric(
                    label = stringResource(R.string.card_metric_mass),
                    value = stringResource(R.string.value_kg, formatNumber(debris.massKg)),
                    modifier = Modifier.weight(1f)
                )
                MiniMetric(
                    label = stringResource(R.string.card_metric_inclination),
                    value = stringResource(R.string.value_degrees, formatNumber(debris.orbitalInclinationDegrees)),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun MiniMetric(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextTertiary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = TextPrimary
        )
    }
}
