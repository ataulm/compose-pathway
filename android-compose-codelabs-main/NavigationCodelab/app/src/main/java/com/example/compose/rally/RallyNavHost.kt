package com.example.compose.rally

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.compose.rally.data.UserData
import com.example.compose.rally.ui.accounts.AccountsBody
import com.example.compose.rally.ui.accounts.SingleAccountBody
import com.example.compose.rally.ui.bills.BillsBody
import com.example.compose.rally.ui.overview.OverviewBody

@Composable
internal fun RallyNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        modifier = Modifier.padding(innerPadding),
        startDestination = RallyScreen.Overview.name,
    ) {
        val accountsName = RallyScreen.Accounts.name
        composable(RallyScreen.Overview.name) {
            OverviewBody(
                onClickSeeAllAccounts = { navController.navigate(accountsName) },
                onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
                onAccountClick = { accountName ->
                    navigateToSingleAccount(
                        navController,
                        accountName
                    )
                }
            )
        }
        composable(route = RallyScreen.Bills.name) {
            BillsBody(bills = UserData.bills)
        }
        composable(route = accountsName) {
            AccountsBody(accounts = UserData.accounts) { accountName ->
                navigateToSingleAccount(navController, accountName)
            }
        }
        composable(
            route = "$accountsName/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "rally://$accountsName/{name}"
                }
            )
        ) { entry ->
            // TODO: error handle unknown name
            val name = entry.arguments?.getString("name")
            val account = UserData.getAccount(name)
            SingleAccountBody(account = account)
        }

    }
}

private fun navigateToSingleAccount(
    navController: NavHostController,
    accountName: String
) {
    navController.navigate("${RallyScreen.Accounts.name}/$accountName")
}