package br.com.fiap.kessler.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.R
import br.com.fiap.kessler.data.repository.DebrisRepository
import br.com.fiap.kessler.ui.components.DebrisCard
import br.com.fiap.kessler.ui.components.FilterChipsRow
import br.com.fiap.kessler.ui.theme.MarsOrange
import br.com.fiap.kessler.ui.theme.SpaceBlack
import br.com.fiap.kessler.ui.theme.SpaceBorder
import br.com.fiap.kessler.ui.theme.TextPrimary
import br.com.fiap.kessler.ui.theme.TextSecondary
import br.com.fiap.kessler.ui.theme.TextTertiary
import br.com.fiap.kessler.viewmodel.CatalogViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel,
    onDebrisClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val all = DebrisRepository.getAll()
    val filtered = viewModel.getFilteredList()
    val totalTonnes = all.sumOf { it.massKg } / 1000.0
    val countriesCount = all.map { it.countryOfOrigin }.distinct().size

    Scaffold(
        containerColor = SpaceBlack,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.catalog_title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.catalog_back_description),
                            tint = TextPrimary
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.FilterList,
                        contentDescription = stringResource(R.string.catalog_filter_description),
                        tint = TextSecondary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
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
        ) {
            StatsRow(
                count = all.size,
                massTonnes = totalTonnes,
                countries = countriesCount
            )
            HorizontalDivider(thickness = 1.dp, color = SpaceBorder)
            FilterChipsRow(
                selectedRisk = viewModel.selectedRisk,
                onSelectRisk = { viewModel.selectRisk(it) }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp)
            ) {
                items(filtered, key = { it.id }) { debris ->
                    DebrisCard(
                        debris = debris,
                        onClick = { onDebrisClick(debris.id) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
private fun StatsRow(count: Int, massTonnes: Double, countries: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 16.dp)
    ) {
        Metric(
            value = count.toString(),
            label = stringResource(R.string.catalog_metric_debris)
        )
        VerticalDivider(color = SpaceBorder, thickness = 1.dp)
        Metric(
            value = stringResource(R.string.value_tonnes, String.format(Locale.US, "%.1f", massTonnes)),
            label = stringResource(R.string.catalog_metric_mass)
        )
        VerticalDivider(color = SpaceBorder, thickness = 1.dp)
        Metric(
            value = countries.toString(),
            label = stringResource(R.string.catalog_metric_countries)
        )
    }
}

@Composable
private fun RowScope.Metric(value: String, label: String) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = MarsOrange
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextTertiary
        )
    }
}
