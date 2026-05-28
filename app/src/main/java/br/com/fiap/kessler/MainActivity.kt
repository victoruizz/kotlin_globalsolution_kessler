package br.com.fiap.kessler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import br.com.fiap.kessler.navigation.AppNavigation
import br.com.fiap.kessler.ui.theme.KesslerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KesslerTheme {
                AppNavigation()
            }
        }
    }
}
