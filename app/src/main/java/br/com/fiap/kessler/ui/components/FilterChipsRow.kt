package br.com.fiap.kessler.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import br.com.fiap.kessler.ui.theme.MarsOrange
import br.com.fiap.kessler.ui.theme.SpaceBlack
import br.com.fiap.kessler.ui.theme.SpaceBorder
import br.com.fiap.kessler.ui.theme.TextSecondary

@Composable
fun FilterChipsRow(
    selectedRisk: RiskLevel?,
    onSelectRisk: (RiskLevel?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) {
        item {
            RiskChip(
                label = stringResource(R.string.catalog_filter_all),
                selected = selectedRisk == null,
                onClick = { onSelectRisk(null) }
            )
        }
        items(RiskLevel.entries) { risk ->
            RiskChip(
                label = risk.label.uppercase(),
                selected = selectedRisk == risk,
                onClick = { onSelectRisk(risk) }
            )
        }
    }
}

@Composable
private fun RiskChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val background = if (selected) MarsOrange else Color.Transparent
    val foreground = if (selected) SpaceBlack else TextSecondary
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        color = background,
        contentColor = foreground,
        border = if (selected) null else BorderStroke(1.dp, SpaceBorder)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = foreground,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
