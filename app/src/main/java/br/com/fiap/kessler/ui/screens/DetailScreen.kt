package br.com.fiap.kessler.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.R
import br.com.fiap.kessler.data.model.Debris
import br.com.fiap.kessler.ui.components.AltitudeBar
import br.com.fiap.kessler.ui.components.RiskBadge
import br.com.fiap.kessler.ui.components.formatNumber
import br.com.fiap.kessler.ui.theme.MarsOrange
import br.com.fiap.kessler.ui.theme.SpaceBlack
import br.com.fiap.kessler.ui.theme.TextPrimary
import br.com.fiap.kessler.ui.theme.TextSecondary
import br.com.fiap.kessler.ui.theme.TextTertiary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    debris: Debris,
    onBack: () -> Unit,
    onAdopt: () -> Unit
) {
    Scaffold(
        containerColor = SpaceBlack,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = debris.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.detail_back_description),
                            tint = TextPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SpaceBlack,
                    titleContentColor = TextPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.detail_id, debris.id),
                style = MaterialTheme.typography.labelSmall,
                color = TextTertiary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = debris.name,
                style = MaterialTheme.typography.displayMedium,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = debris.countryOfOrigin,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(32.dp))
            RiskBadge(level = debris.riskLevel)
            Spacer(modifier = Modifier.height(24.dp))
            AltitudeBar(altitudeKm = debris.altitudeKm)
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                InfoCell(
                    label = stringResource(R.string.detail_label_mass),
                    value = formatNumber(debris.massKg)
                )
                InfoCell(
                    label = stringResource(R.string.detail_label_inclination),
                    value = stringResource(R.string.value_degrees, formatNumber(debris.orbitalInclinationDegrees))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                InfoCell(
                    label = stringResource(R.string.detail_label_year),
                    value = debris.launchYear.toString()
                )
                InfoCell(
                    label = stringResource(R.string.detail_label_type),
                    value = debris.type
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.detail_section_history),
                style = MaterialTheme.typography.labelLarge,
                color = MarsOrange
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = debris.story,
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = onAdopt,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MarsOrange,
                    contentColor = SpaceBlack
                ),
                elevation = null
            ) {
                Text(
                    text = stringResource(R.string.detail_cta),
                    style = MaterialTheme.typography.labelLarge,
                    color = SpaceBlack
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun RowScope.InfoCell(label: String, value: String) {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextTertiary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )
    }
}
