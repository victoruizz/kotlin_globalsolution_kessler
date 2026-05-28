package br.com.fiap.kessler.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.kessler.R
import br.com.fiap.kessler.ui.components.OrbitalDecoration
import br.com.fiap.kessler.ui.theme.MarsOrange
import br.com.fiap.kessler.ui.theme.SpaceBlack
import br.com.fiap.kessler.ui.theme.TextPrimary
import br.com.fiap.kessler.ui.theme.TextSecondary
import br.com.fiap.kessler.ui.theme.TextTertiary

@Composable
fun HomeScreen(onExplore: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SpaceBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.home_header_left),
                    style = MaterialTheme.typography.labelLarge,
                    color = MarsOrange
                )
                Text(
                    text = stringResource(R.string.home_header_right),
                    style = MaterialTheme.typography.labelSmall,
                    color = TextTertiary
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            OrbitalDecoration(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(R.string.home_eyebrow),
                style = MaterialTheme.typography.labelLarge,
                color = MarsOrange
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.home_wordmark),
                style = MaterialTheme.typography.displayLarge,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.home_tagline),
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.home_description),
                style = MaterialTheme.typography.bodyMedium,
                color = TextTertiary,
                maxLines = 3
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onExplore,
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
                    text = stringResource(R.string.home_cta),
                    style = MaterialTheme.typography.labelLarge,
                    color = SpaceBlack
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.home_footer),
                style = MaterialTheme.typography.labelSmall,
                color = TextTertiary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
