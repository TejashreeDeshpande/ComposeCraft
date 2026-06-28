package com.example.composecraft.features.pulseinvest.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.*
import com.example.composecraft.features.pulseinvest.ui.screen.account.*
import com.example.composecraft.features.pulseinvest.ui.screen.auth.*
import com.example.composecraft.features.pulseinvest.ui.screen.dashboard.*
import com.example.composecraft.features.pulseinvest.ui.screen.funds.*
import com.example.composecraft.features.pulseinvest.ui.screen.portfolio.*
import com.example.composecraft.features.pulseinvest.ui.screen.stock.*
import com.example.composecraft.features.pulseinvest.ui.screen.support.*
import com.example.composecraft.features.pulseinvest.ui.theme.PulseDark
import com.example.composecraft.features.pulseinvest.ui.theme.PulseGreen
import com.example.composecraft.features.pulseinvest.ui.theme.PulseSurface
import com.example.composecraft.features.pulseinvest.ui.theme.PulseTextSecondary
import org.koin.androidx.compose.koinViewModel

// ── Route definitions ─────────────────────────────────────────────────────────
sealed class PulseRoute(val route: String) {
    object Splash          : PulseRoute("splash")
    object Onboarding      : PulseRoute("onboarding")
    object SignUp          : PulseRoute("signup")
    object VerifyEmail     : PulseRoute("verify_email/{email}") { fun create(email: String) = "verify_email/$email" }
    object SetSecurity     : PulseRoute("set_security")
    object EnableBiometric : PulseRoute("enable_biometric")

    object Home         : PulseRoute("home")
    object Search       : PulseRoute("search")
    object StockDetail  : PulseRoute("stock_detail/{symbol}") { fun create(s: String) = "stock_detail/$s" }
    object BuySell      : PulseRoute("buy_sell/{symbol}")     { fun create(s: String) = "buy_sell/$s" }
    object OrderReview  : PulseRoute("order_review")
    object OrderSuccess : PulseRoute("order_success")

    object Portfolio : PulseRoute("portfolio")
    object Holdings  : PulseRoute("holdings")
    object Watchlist : PulseRoute("watchlist")

    object AiCoach     : PulseRoute("ai_coach")
    object MarketNews  : PulseRoute("market_news")
    object LearningHub : PulseRoute("learning_hub")

    object DepositFunds  : PulseRoute("deposit_funds")
    object WithdrawFunds : PulseRoute("withdraw_funds")

    object Notifications  : PulseRoute("notifications")
    object Alerts         : PulseRoute("alerts")
    object Profile        : PulseRoute("profile")
    object Settings       : PulseRoute("settings")
    object SecurityCenter : PulseRoute("security_center")
    object Statements     : PulseRoute("statements")
    object HelpCenter     : PulseRoute("help_center")
    object SupportChat    : PulseRoute("support_chat")
}

data class BottomNavItem(val route: String, val label: String, val emoji: String)

val bottomNavItems = listOf(
    BottomNavItem(PulseRoute.Home.route,      "Home",      "🏠"),
    BottomNavItem(PulseRoute.Portfolio.route, "Portfolio", "📊"),
    BottomNavItem(PulseRoute.AiCoach.route,   "AI Coach",  "🤖"),
    BottomNavItem(PulseRoute.Watchlist.route, "Watchlist", "📰"),
    BottomNavItem(PulseRoute.Profile.route,   "Profile",   "👤")
)

private val mainRoutes = bottomNavItems.map { it.route }.toSet()

// ── Nav graph ─────────────────────────────────────────────────────────────────
@Composable
fun PulseNavGraph(
    startDestination: String = PulseRoute.Splash.route,
    onExit: () -> Unit = {}
) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    Scaffold(
        containerColor = PulseDark,
        bottomBar = {
            if (currentRoute in mainRoutes) {
                NavigationBar(containerColor = PulseSurface, tonalElevation = 0.dp) {
                    bottomNavItems.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick  = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(PulseRoute.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState    = true
                                    }
                                }
                            },
                            icon   = { Text(item.emoji, fontSize = 20.sp) },
                            label  = { Text(item.label, fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor   = PulseGreen,
                                selectedTextColor   = PulseGreen,
                                unselectedIconColor = PulseTextSecondary,
                                unselectedTextColor = PulseTextSecondary,
                                indicatorColor      = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = startDestination,
            modifier         = Modifier.padding(innerPadding)
        ) {

            // ── Auth ─────────────────────────────────────────────────────────
            composable(PulseRoute.Splash.route) {
                SplashScreen(
                    onGetStarted = { navController.navigate(PulseRoute.Onboarding.route) },
                    onLogin      = { navController.navigate(PulseRoute.SignUp.route) },
                    onExit       = onExit
                )
            }
            composable(PulseRoute.Onboarding.route) {
                OnboardingScreen(
                    onNext = { navController.navigate(PulseRoute.SignUp.route) },
                    onSkip = { navController.navigate(PulseRoute.SignUp.route) }
                )
            }
            composable(PulseRoute.SignUp.route) {
                val vm: AuthViewModel = koinViewModel()
                SignUpScreen(
                    vm        = vm,
                    onSuccess = { email -> navController.navigate(PulseRoute.VerifyEmail.create(email)) },
                    onLogin   = { navController.popBackStack() }
                )
            }
            composable(
                route     = PulseRoute.VerifyEmail.route,
                arguments = listOf(navArgument("email") { type = NavType.StringType })
            ) { back ->
                val email = back.arguments?.getString("email") ?: ""
                VerifyEmailScreen(
                    email      = email,
                    onContinue = { navController.navigate(PulseRoute.SetSecurity.route) }
                )
            }
            composable(PulseRoute.SetSecurity.route) {
                val vm: AuthViewModel = koinViewModel()
                SetSecurityScreen(vm = vm, onSuccess = { navController.navigate(PulseRoute.EnableBiometric.route) })
            }
            composable(PulseRoute.EnableBiometric.route) {
                val vm: AuthViewModel = koinViewModel()
                EnableBiometricScreen(
                    vm        = vm,
                    onSuccess = { navController.navigate(PulseRoute.Home.route) { popUpTo(0) } },
                    onSkip    = { navController.navigate(PulseRoute.Home.route) { popUpTo(0) } }
                )
            }

            // ── Dashboard ─────────────────────────────────────────────────────
            composable(PulseRoute.Home.route) {
                val vm: HomeViewModel = koinViewModel()
                HomeScreen(
                    vm                   = vm,
                    onStockClick         = { sym -> navController.navigate(PulseRoute.StockDetail.create(sym)) },
                    onSearchClick        = { navController.navigate(PulseRoute.Search.route) },
                    onNotificationsClick = { navController.navigate(PulseRoute.Notifications.route) }
                )
            }
            composable(PulseRoute.Search.route) {
                val vm: StockViewModel = koinViewModel()
                SearchScreen(
                    vm           = vm,
                    onBack       = { navController.popBackStack() },
                    onStockClick = { sym -> navController.navigate(PulseRoute.StockDetail.create(sym)) }
                )
            }
            composable(
                route     = PulseRoute.StockDetail.route,
                arguments = listOf(navArgument("symbol") { type = NavType.StringType })
            ) { back ->
                val sym = back.arguments?.getString("symbol") ?: ""
                val vm: StockViewModel = koinViewModel()
                StockDetailScreen(
                    symbol = sym, vm = vm,
                    onBack = { navController.popBackStack() },
                    onBuy  = { navController.navigate(PulseRoute.BuySell.create(sym)) }
                )
            }
            composable(
                route     = PulseRoute.BuySell.route,
                arguments = listOf(navArgument("symbol") { type = NavType.StringType })
            ) { back ->
                val sym = back.arguments?.getString("symbol") ?: ""
                val vm: StockViewModel = koinViewModel()
                BuySellScreen(
                    symbol        = sym, vm = vm,
                    onBack        = { navController.popBackStack() },
                    onReviewOrder = { navController.navigate(PulseRoute.OrderReview.route) }
                )
            }
            composable(PulseRoute.OrderReview.route) {
                val vm: StockViewModel = koinViewModel()
                OrderReviewScreen(
                    vm            = vm,
                    onBack        = { navController.popBackStack() },
                    onOrderPlaced = {
                        navController.navigate(PulseRoute.OrderSuccess.route) {
                            popUpTo(PulseRoute.Home.route)
                        }
                    }
                )
            }
            composable(PulseRoute.OrderSuccess.route) {
                val vm: StockViewModel = koinViewModel()
                OrderSuccessScreen(
                    vm              = vm,
                    onViewPortfolio = { navController.navigate(PulseRoute.Portfolio.route) { popUpTo(PulseRoute.Home.route) } },
                    onBackToHome    = { navController.navigate(PulseRoute.Home.route) { popUpTo(0) } }
                )
            }

            // ── Portfolio ─────────────────────────────────────────────────────
            composable(PulseRoute.Portfolio.route) {
                val vm: PortfolioViewModel = koinViewModel()
                PortfolioScreen(
                    vm             = vm,
                    onStockClick   = { sym -> navController.navigate(PulseRoute.StockDetail.create(sym)) },
                    onViewHoldings = { navController.navigate(PulseRoute.Holdings.route) }
                )
            }
            composable(PulseRoute.Holdings.route) {
                val vm: PortfolioViewModel = koinViewModel()
                HoldingsScreen(
                    vm           = vm,
                    onBack       = { navController.popBackStack() },
                    onStockClick = { sym -> navController.navigate(PulseRoute.StockDetail.create(sym)) }
                )
            }
            composable(PulseRoute.Watchlist.route) {
                val vm: PortfolioViewModel = koinViewModel()
                WatchlistScreen(
                    vm           = vm,
                    onStockClick = { sym -> navController.navigate(PulseRoute.StockDetail.create(sym)) }
                )
            }

            // ── AI & Content ──────────────────────────────────────────────────
            composable(PulseRoute.AiCoach.route)    { AiCoachScreen() }
            composable(PulseRoute.MarketNews.route) {
                val vm: HomeViewModel = koinViewModel()
                MarketNewsScreen(vm = vm, onBack = { navController.popBackStack() })
            }
            composable(PulseRoute.LearningHub.route) {
                val vm: LearningViewModel = koinViewModel()
                LearningHubScreen(vm = vm, onBack = { navController.popBackStack() })
            }

            // ── Funds ─────────────────────────────────────────────────────────
            composable(PulseRoute.DepositFunds.route) {
                val vm: FundsViewModel = koinViewModel()
                DepositFundsScreen(vm = vm, onBack = { navController.popBackStack() })
            }
            composable(PulseRoute.WithdrawFunds.route) {
                val vm: FundsViewModel = koinViewModel()
                WithdrawFundsScreen(vm = vm, onBack = { navController.popBackStack() })
            }

            // ── Account ───────────────────────────────────────────────────────
            composable(PulseRoute.Notifications.route) {
                val vm: NotificationViewModel = koinViewModel()
                NotificationsScreen(vm = vm, onBack = { navController.popBackStack() })
            }
            composable(PulseRoute.Alerts.route) {
                val vm: NotificationViewModel = koinViewModel()
                AlertsScreen(vm = vm, onBack = { navController.popBackStack() })
            }
            composable(PulseRoute.Profile.route) {
                ProfileScreen(onNavigate = { route -> navController.navigate(route) })
            }
            composable(PulseRoute.Settings.route) {
                SettingsScreen(onBack = { navController.popBackStack() })
            }
            composable(PulseRoute.SecurityCenter.route) {
                SecurityCenterScreen(onBack = { navController.popBackStack() })
            }
            composable(PulseRoute.Statements.route) {
                val vm: AccountViewModel = koinViewModel()
                StatementsScreen(vm = vm, onBack = { navController.popBackStack() })
            }

            // ── Support ───────────────────────────────────────────────────────
            composable(PulseRoute.HelpCenter.route) {
                HelpCenterScreen(
                    onBack           = { navController.popBackStack() },
                    onContactSupport = { navController.navigate(PulseRoute.SupportChat.route) }
                )
            }
            composable(PulseRoute.SupportChat.route) {
                val vm: SupportViewModel = koinViewModel()
                SupportChatScreen(vm = vm, onBack = { navController.popBackStack() })
            }
        }
    }
}
