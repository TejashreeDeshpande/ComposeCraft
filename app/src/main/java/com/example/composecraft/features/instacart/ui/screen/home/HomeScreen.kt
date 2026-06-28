package com.example.composecraft.features.instacart.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.Category
import com.example.composecraft.features.instacart.domain.model.Deal
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.domain.model.Store
import com.example.composecraft.features.instacart.presentation.state.HomeState
import com.example.composecraft.features.instacart.presentation.viewmodel.HomeViewModel
import com.example.composecraft.features.instacart.ui.components.ProductCard
import com.example.composecraft.features.instacart.ui.components.SectionHeader
import com.example.composecraft.features.instacart.ui.theme.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onProductClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onCartClick: () -> Unit,
    onSearchClick: () -> Unit,
    cartItemCount: Int
) {
    val vm: HomeViewModel = koinViewModel()
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Exit Instacart", tint = InstacartTextPrimary)
                    }
                },
                title = {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(16.dp))
                            Text(
                                " ${state.selectedStore?.name ?: "Select Store"}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = InstacartTextPrimary
                            )
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = InstacartTextSecondary, modifier = Modifier.size(16.dp))
                        }
                        Text("Delivery in 30-60 min", fontSize = 11.sp, color = InstacartGreen)
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = onCartClick) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = InstacartGreen)
                        }
                        if (cartItemCount > 0) {
                            Box(
                                modifier = Modifier
                                    .offset(x = 6.dp, y = (-6).dp)
                                    .size(18.dp)
                                    .background(InstacartBadge, CircleShape)
                                    .align(Alignment.TopEnd),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("$cartItemCount", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InstacartSurface)
            )
        },
        containerColor = InstacartBg
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                SearchBar(onClick = onSearchClick)
            }

            item {
                StoreSelector(stores = state.stores, selected = state.selectedStore, onSelect = vm::selectStore)
            }

            item {
                HeroBanner()
            }

            item {
                SectionHeader("Shop by Category")
            }
            item {
                CategoryGrid(categories = state.categories, onCategoryClick = onCategoryClick)
            }

            if (state.deals.isNotEmpty()) {
                item { SectionHeader("Today's Deals", "See all") {} }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.deals) { deal ->
                            DealCard(deal = deal, onClick = { onProductClick(deal.product.id) })
                        }
                    }
                }
            }

            item { SectionHeader("Popular Items", "See all") {} }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.featuredProducts) { product ->
                        ProductCard(
                            product = product,
                            onAddToCart = { vm.addToCart(product) },
                            onClick = { onProductClick(product.id) }
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
            item {
                FreshPicksBanner(onShopNow = { onCategoryClick("fruits") })
            }
        }
    }
}

@Composable
private fun SearchBar(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(48.dp)
            .background(InstacartSurface, RoundedCornerShape(24.dp))
            .border(1.dp, InstacartDivider, RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Icon(Icons.Default.Search, contentDescription = null, tint = InstacartGreen, modifier = Modifier.size(20.dp))
            Text("Search products, brands...", fontSize = 14.sp, color = InstacartTextSecondary)
        }
    }
}

@Composable
private fun StoreSelector(stores: List<Store>, selected: Store?, onSelect: (Store) -> Unit) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stores.forEach { store ->
            val isSelected = store.id == selected?.id
            Box(
                modifier = Modifier
                    .background(
                        if (isSelected) InstacartGreenLight else InstacartSurface,
                        RoundedCornerShape(20.dp)
                    )
                    .border(
                        1.dp,
                        if (isSelected) InstacartGreen else InstacartDivider,
                        RoundedCornerShape(20.dp)
                    )
                    .clickable { onSelect(store) }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(store.emoji, fontSize = 16.sp)
                    Text(
                        store.name,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) InstacartGreen else InstacartTextPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(InstacartGreen, InstacartGreenDark)))
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text("Free delivery", color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
            Text("on orders over $35", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 14.dp, vertical = 7.dp)
            ) {
                Text("Shop now", color = InstacartGreen, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
        Text("🛒", fontSize = 64.sp, modifier = Modifier.align(Alignment.CenterEnd))
    }
}

@Composable
private fun CategoryGrid(categories: List<Category>, onCategoryClick: (String) -> Unit) {
    val rows = categories.chunked(4)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        rows.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                row.forEach { category ->
                    CategoryChip(category = category, onClick = { onCategoryClick(category.id) }, modifier = Modifier.weight(1f))
                }
                repeat(4 - row.size) { Spacer(Modifier.weight(1f)) }
            }
        }
    }
}

@Composable
private fun CategoryChip(category: Category, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(category.color).copy(alpha = 0.12f))
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(category.emoji, fontSize = 26.sp)
        Text(
            category.name.split(" ").first(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = InstacartTextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun DealCard(deal: Deal, onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(140.dp).clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp).background(Color(0xFFFFF3E0)),
                contentAlignment = Alignment.Center
            ) {
                Text(deal.product.imageEmoji, fontSize = 44.sp)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .background(InstacartBadge, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text("-${deal.discountPercent}%", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(deal.product.name, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 15.sp)
                Spacer(Modifier.height(4.dp))
                Text(
                    "$${String.format("%.2f", deal.product.price)}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = InstacartGreen
                )
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Default.Timer, contentDescription = null, tint = InstacartBadge, modifier = Modifier.size(12.dp))
                    Text(deal.expiresIn, fontSize = 10.sp, color = InstacartBadge)
                }
            }
        }
    }
}

@Composable
private fun FreshPicksBanner(onShopNow: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(Color(0xFF43A047), Color(0xFF1B5E20))))
            .clickable { onShopNow() }
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Fresh & Organic", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                Text("Farm to table picks", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Shop now →", color = InstacartYellow, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
            Text("🥦🍎🥕", fontSize = 36.sp)
        }
    }
}
