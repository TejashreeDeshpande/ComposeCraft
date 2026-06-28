package com.example.composecraft.features.instacart.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecraft.features.instacart.data.datasource.InstacartMockDataSource
import com.example.composecraft.features.instacart.domain.model.Order
import com.example.composecraft.features.instacart.domain.repository.CartRepository
import com.example.composecraft.features.instacart.ui.screen.browse.BrowseScreen
import com.example.composecraft.features.instacart.ui.screen.cart.CartScreen
import com.example.composecraft.features.instacart.ui.screen.checkout.CheckoutScreen
import com.example.composecraft.features.instacart.ui.screen.checkout.OrderConfirmationScreen
import com.example.composecraft.features.instacart.ui.screen.home.HomeScreen
import com.example.composecraft.features.instacart.ui.screen.orders.OrdersScreen
import com.example.composecraft.features.instacart.ui.screen.product.ProductDetailScreen
import com.example.composecraft.features.instacart.ui.theme.InstacartTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun InstacartNavGraph(onExit: () -> Unit) {
    InstacartTheme {
        val navController = rememberNavController()
        val cartRepo: CartRepository = koinInject()
        val cartState by cartRepo.cart.collectAsState()
        var placedOrder by remember { mutableStateOf<Order?>(null) }

        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    onBack = onExit,
                    onProductClick = { id -> navController.navigate("product/$id") },
                    onCategoryClick = { categoryId ->
                        navController.navigate("browse") {
                            launchSingleTop = true
                        }
                    },
                    onCartClick = { navController.navigate("cart") },
                    onSearchClick = { navController.navigate("browse") },
                    cartItemCount = cartState.itemCount
                )
            }

            composable("browse") {
                BrowseScreen(
                    categories = InstacartMockDataSource.categories,
                    onProductClick = { id -> navController.navigate("product/$id") },
                    onBack = { navController.popBackStack() },
                    onAddToCart = {}
                )
            }

            composable("product/{productId}") { backStack ->
                val productId = backStack.arguments?.getString("productId") ?: return@composable
                ProductDetailScreen(
                    productId = productId,
                    onBack = { navController.popBackStack() },
                    onCartClick = { navController.navigate("cart") },
                    onProductClick = { id -> navController.navigate("product/$id") }
                )
            }

            composable("cart") {
                CartScreen(
                    onBack = { navController.popBackStack() },
                    onCheckout = { navController.navigate("checkout") }
                )
            }

            composable("checkout") {
                CheckoutScreen(
                    onBack = { navController.popBackStack() },
                    onOrderPlaced = { orderId ->
                        navController.navigate("confirmation") {
                            popUpTo("home")
                        }
                    }
                )
            }

            composable("confirmation") {
                val checkoutVm: com.example.composecraft.features.instacart.presentation.viewmodel.CheckoutViewModel = koinViewModel()
                val checkoutState by checkoutVm.state.collectAsState()
                val order = checkoutState.placedOrder
                if (order != null) {
                    OrderConfirmationScreen(
                        order = order,
                        onContinueShopping = {
                            navController.navigate("home") { popUpTo("home") { inclusive = true } }
                        },
                        onViewOrders = {
                            navController.navigate("orders")
                        }
                    )
                }
            }

            composable("orders") {
                OrdersScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
