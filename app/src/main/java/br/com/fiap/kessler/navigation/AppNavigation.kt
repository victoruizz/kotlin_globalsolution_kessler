package br.com.fiap.kessler.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.kessler.data.repository.DebrisRepository
import br.com.fiap.kessler.ui.screens.CatalogScreen
import br.com.fiap.kessler.ui.screens.ConfirmationScreen
import br.com.fiap.kessler.ui.screens.DetailScreen
import br.com.fiap.kessler.ui.screens.HomeScreen
import br.com.fiap.kessler.viewmodel.CatalogViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Routes.HOME) {
            HomeScreen(onExplore = { navController.navigate(Routes.CATALOG) })
        }
        composable(Routes.CATALOG) {
            val catalogViewModel: CatalogViewModel = viewModel()
            CatalogScreen(
                viewModel = catalogViewModel,
                onDebrisClick = { id -> navController.navigate(Routes.detail(id)) }
            )
        }
        composable(Routes.DETAIL) { backStackEntry ->
            val debrisId = backStackEntry.arguments?.getString("debrisId")
            val debris = DebrisRepository.getAll().firstOrNull { it.id == debrisId }
            if (debris != null) {
                DetailScreen(
                    debris = debris,
                    onBack = { navController.popBackStack() },
                    onAdopt = { navController.navigate(Routes.confirmation(debris.id)) }
                )
            }
        }
        composable(Routes.CONFIRMATION) { backStackEntry ->
            val debrisId = backStackEntry.arguments?.getString("debrisId")
            val debris = DebrisRepository.getAll().firstOrNull { it.id == debrisId }
            if (debris != null) {
                ConfirmationScreen(
                    debris = debris,
                    onBackToCatalog = {
                        navController.navigate(Routes.CATALOG) {
                            popUpTo(Routes.HOME)
                        }
                    },
                    onBackToHome = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
