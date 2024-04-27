package com.example.rally

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument

interface RallyDestination {
    val icon:ImageVector
    val route:String
}

object Overview : RallyDestination {
    override val icon= Icons.Filled.PieChart
    override val route="overview"
}

object Accounts : RallyDestination {
    override val icon= Icons.Filled.Refresh

    override val route="accounts"
}

object Bills : RallyDestination {
    override val icon= Icons.Filled.MailOutline
    override val route= "bills"
}

object SingleAccount : RallyDestination {
    override val icon= Icons.Filled.AddCircle
    override val route="single_account"
    const val accountTypeArg="account_type"
    val routeWithArgs="$route/{$accountTypeArg}"
    val  arguments=listOf(
        navArgument(accountTypeArg){type=NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink{uriPattern="rally://$route/{$accountTypeArg}"}
    )
}
