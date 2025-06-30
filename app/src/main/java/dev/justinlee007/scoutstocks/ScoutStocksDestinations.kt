/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.justinlee007.scoutstocks

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Contract for information needed on every Scout Stock navigation destination
 */
interface ScoutStocksDestination {
    val route: String
}

/**
 * Scout Stocks app navigation destinations
 */
object Overview : ScoutStocksDestination {
    override val route = "overview"
}

object StockDetail : ScoutStocksDestination {
    override val route = "stock_detail"
    const val tickerArg = "ticker"

    val routeWithArgs = "${route}/{${tickerArg}}"

    val arguments = listOf(
        navArgument(tickerArg) { type = NavType.StringType }
    )
}

object StockList : ScoutStocksDestination {
    override val route = "stock_list"
}

object AddStock : ScoutStocksDestination {
    override val route = "add_stock"
}

/**
 * Models the navigation actions in the app.
 */
class ScoutStocksNavigationActions(private val navController: NavHostController) {

    fun navigateToStockList() {
        navController.navigate(route = StockList.route)
    }

    fun navigateToStockDetail(ticker: String) {
        navController.navigate(route = "${StockDetail.route}/$ticker")
    }

    fun navigateToAddStock() {
        navController.navigate(route = AddStock.route)
    }
}

