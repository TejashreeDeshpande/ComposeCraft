package com.example.composecraft.features.instacart.ui.screen.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.presentation.viewmodel.ProductDetailViewModel
import com.example.composecraft.features.instacart.ui.components.ProductCard
import com.example.composecraft.features.instacart.ui.components.SectionHeader
import com.example.composecraft.features.instacart.ui.theme.*
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    onBack: () -> Unit,
    onCartClick: () -> Unit,
    onProductClick: (String) -> Unit
) {
    val vm: ProductDetailViewModel = koinViewModel(parameters = { parametersOf(productId) })
    val state by vm.state.collectAsState()
    val product = state.product ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product.brand, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onCartClick) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = InstacartGreen)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InstacartSurface)
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp, color = InstacartSurface) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("$${String.format("%.2f", product.price)}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Text(product.unit, fontSize = 13.sp, color = InstacartTextSecondary)
                    }
                    Button(
                        onClick = vm::addToCart,
                        colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(52.dp).weight(1.5f)
                    ) {
                        Icon(Icons.Default.AddShoppingCart, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(
                            if (state.quantityInCart > 0) "Add more (${state.quantityInCart})" else "Add to cart",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        containerColor = InstacartBg
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().height(260.dp).background(InstacartSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(product.imageEmoji, fontSize = 100.sp)
                    if (product.isOrganic) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp)
                                .background(InstacartGreen, RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text("USDA Organic", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    if (product.originalPrice != null) {
                        val pct = ((1 - product.price / product.originalPrice) * 100).toInt()
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                                .background(InstacartBadge, RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text("Save $pct%", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = InstacartSurface),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(product.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(product.brand, fontSize = 14.sp, color = InstacartTextSecondary, modifier = Modifier.padding(top = 4.dp))

                        Row(
                            modifier = Modifier.padding(top = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(5) { i ->
                                    Icon(
                                        Icons.Default.Star,
                                        contentDescription = null,
                                        tint = if (i < product.rating.toInt()) InstacartYellow else InstacartDivider,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(Modifier.width(6.dp))
                                Text("${product.rating}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                                Text(" (${product.reviewCount})", fontSize = 13.sp, color = InstacartTextSecondary)
                            }
                        }

                        if (product.tags.isNotEmpty()) {
                            Row(
                                modifier = Modifier.padding(top = 12.dp).horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                product.tags.forEach { tag ->
                                    Box(
                                        modifier = Modifier
                                            .background(InstacartGreenLight, RoundedCornerShape(16.dp))
                                            .padding(horizontal = 10.dp, vertical = 5.dp)
                                    ) {
                                        Text(tag, fontSize = 12.sp, color = InstacartGreen, fontWeight = FontWeight.Medium)
                                    }
                                }
                            }
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = InstacartDivider)

                        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                            InfoItem(emoji = "🚚", label = "Delivery", value = "30-60 min")
                            InfoItem(emoji = "🏷️", label = "Per unit", value = product.unit)
                            InfoItem(emoji = "⭐", label = "Rating", value = "${product.rating}/5")
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = InstacartDivider)

                        Text("About this item", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Fresh ${product.name} from ${product.brand}. " +
                            "${if (product.isOrganic) "Certified organic and " else ""}Available in ${product.unit}. " +
                            "Rated ${product.rating} stars by ${product.reviewCount} customers.",
                            fontSize = 14.sp,
                            color = InstacartTextSecondary,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            if (state.similarProducts.isNotEmpty()) {
                item { SectionHeader("You might also like") }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.similarProducts) { similar ->
                            ProductCard(
                                product = similar,
                                onAddToCart = {},
                                onClick = { onProductClick(similar.id) }
                            )
                        }
                    }
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun InfoItem(emoji: String, label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(emoji, fontSize = 22.sp)
        Text(label, fontSize = 11.sp, color = InstacartTextSecondary)
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}
