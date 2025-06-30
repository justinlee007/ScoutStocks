package dev.justinlee007.scoutstocks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.justinlee007.scoutstocks.ui.compose.AddStockScreen
import dev.justinlee007.scoutstocks.ui.compose.OverviewScreen
import dev.justinlee007.scoutstocks.ui.compose.StockDetailScreen
import dev.justinlee007.scoutstocks.ui.compose.StockListScreen

@Composable
fun ScoutStocksNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navActions: ScoutStocksNavigationActions = remember(navController) {
        ScoutStocksNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
            OverviewScreen(
                onClickStockDetail = { ticker ->
                    navActions.navigateToStockDetail(ticker)
                },
                onClickStockList = {
                    navActions.navigateToStockList()
                }
            )
        }
        composable(
            route = StockDetail.routeWithArgs,
            arguments = StockDetail.arguments,
        ) {
            StockDetailScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = StockList.route) {
            StockListScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = AddStock.route) {
            AddStockScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}