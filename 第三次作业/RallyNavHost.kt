package com.example.rally

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.rally.Accounts
import com.example.rally.Bills
import com.example.rally.Overview
import com.example.rally.SingleAccount
import com.example.compose.rally.ui.overview.OverviewScreen as OverviewScreen1

@Composable
fun RallyNavHost(
    navController =NavHostController,
    modifier: Modifier=Modifier

){
    NavHost(
        navController=navController,
        starDestination= Overview.route,
        modifier=modifier
    ){
        composable(route= Overview.route){
            OverviewScreen1(
                onClickSeeAllAccounts = {
                    navController.navigateSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigateSingleTopTo(Bills.route)
                },
                onAccountClick = { accountType ->
                    navController.navigateSingleAccount(accountType)
                }
            )
        }
        composable(route= Accounts.route){
            AccountsScreen(
                onAccountClick = { accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Bills.route){
            BillsScreen()
        }
        composable(
            route= SingleAccount.routeWithArgs,
            arguments= SingleAccount.arguments,
            deepLinks= SingleAccount.deepLinks
        ){
            navBackStackEntry ->
            val accountType=
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType )
        }
    }
}

fun NavHostController.navigateSingTopTo(route:String)=
    this.navigate(route){
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ){
            saveState=true
        }
        launchSingleTop=true
        restoreState=true
    }

private fun NavHostController.navigateToSingleAccount(accountType:String){
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}
