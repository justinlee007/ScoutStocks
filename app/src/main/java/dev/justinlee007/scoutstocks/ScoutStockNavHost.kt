package dev.justinlee007.scoutstocks

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.justinlee007.scoutstocks.ui.compose.AddStockScreen
import dev.justinlee007.scoutstocks.ui.compose.OverviewScreen
import dev.justinlee007.scoutstocks.ui.compose.StockDetailScreen
import dev.justinlee007.scoutstocks.ui.compose.StockListScreen

@Composable
fun ScoutStockNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
            OverviewScreen(
//                onClickSeeAllAccounts = {
//                    navController.navigateSingleTopTo(Accounts.route)
//                },
//                onClickSeeAllBills = {
//                    navController.navigateSingleTopTo(Bills.route)
//                },
//                onAccountClick = { accountType ->
//                    navController.navigateToSingleAccount(accountType)
//                }
            )
        }
        composable(route = StockDetail.route) {
            StockDetailScreen(
//                onAccountClick = { accountType ->
//                    navController.navigateToSingleAccount(accountType)
//                }
            )
        }
        composable(route = StockList.route) {
            StockListScreen()
        }
        composable(route = AddStock.route) {
            AddStockScreen()
        }
    }
}