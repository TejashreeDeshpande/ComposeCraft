package com.example.composecraft.features.instacart.ui.screen.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.instacart.domain.model.Category
import com.example.composecraft.features.instacart.domain.model.Product
import com.example.composecraft.features.instacart.presentation.viewmodel.SearchViewModel
import com.example.composecraft.features.instacart.ui.theme.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseScreen(
    categories: List<Category>,
    onProductClick: (String) -> Unit,
    onBack: () -> Unit,
    onAddToCart: (Product) -> Unit
) {
    val vm: SearchViewModel = koinViewModel()
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = state.query,
                        onValueChange = vm::onQueryChange,
                        placeholder = { Text("Search products...", fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = InstacartGreen) },
                        trailingIcon = {
                            if (state.query.isNotEmpty()) {
                                IconButton(onClick = { vm.onQueryChange("") }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = InstacartGreen,
                            unfocusedBorderColor = InstacartDivider
                        ),
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = { })
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InstacartSurface)
            )
        },
        containerColor = InstacartBg
    ) { padding ->
        if (state.isSearching) {
            SearchResults(
                results = state.results,
                query = state.query,
                onProductClick = onProductClick,
                onAddToCart = onAddToCart,
                modifier = Modifier.padding(padding)
            )
        } else if (state.selectedCategoryId != null) {
            val category = categories.find { it.id == state.selectedCategoryId }
            CategoryProductsScreen(
                categoryName = category?.name ?: "",
                products = state.categoryProducts,
                onProductClick = onProductClick,
                onAddToCart = onAddToCart,
                onBack = vm::clearCategory,
                modifier = Modifier.padding(padding)
            )
        } else {
            AllCategoriesGrid(
                categories = categories,
                onCategoryClick = vm::selectCategory,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun AllCategoriesGrid(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            "All Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                CategoryGridCard(category = category, onClick = { onCategoryClick(category.id) })
            }
        }
    }
}

@Composable
private fun CategoryGridCard(category: Category, onClick: () -> Unit) {
    val bgColor = Color(category.color).copy(alpha = 0.12f)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(bgColor)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(category.emoji, fontSize = 32.sp)
            Text(
                category.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = InstacartTextPrimary,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun SearchResults(
    results: List<Product>,
    query: String,
    onProductClick: (String) -> Unit,
    onAddToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(
                "${results.size} results for \"$query\"",
                fontSize = 14.sp,
                color = InstacartTextSecondary
            )
        }
        items(results) { product ->
            ProductListItem(product = product, onClick = { onProductClick(product.id) }, onAddToCart = { onAddToCart(product) })
        }
        if (results.isEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("🔍", fontSize = 48.sp)
                    Spacer(Modifier.height(16.dp))
                    Text("No results found", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Try a different search term", fontSize = 14.sp, color = InstacartTextSecondary)
                }
            }
        }
    }
}

@Composable
private fun CategoryProductsScreen(
    categoryName: String,
    products: List<Product>,
    onProductClick: (String) -> Unit,
    onAddToCart: (Product) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.clickable { onBack() }, tint = InstacartGreen)
                Text(categoryName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
        items(products) { product ->
            ProductListItem(product = product, onClick = { onProductClick(product.id) }, onAddToCart = { onAddToCart(product) })
        }
    }
}

@Composable
fun ProductListItem(product: Product, onClick: () -> Unit, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = InstacartSurface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier.size(72.dp).background(InstacartBg, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(product.imageEmoji, fontSize = 36.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                if (product.isOrganic) {
                    Text("Organic", fontSize = 10.sp, color = InstacartGreen, fontWeight = FontWeight.Bold)
                }
                Text(product.name, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(product.brand, fontSize = 12.sp, color = InstacartTextSecondary)
                Text(product.unit, fontSize = 12.sp, color = InstacartTextSecondary)
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                    Text("$${String.format("%.2f", product.price)}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    if (product.originalPrice != null) {
                        Text("$${String.format("%.2f", product.originalPrice)}", fontSize = 13.sp, color = InstacartTextSecondary)
                    }
                }
            }
            Button(
                onClick = onAddToCart,
                colors = ButtonDefaults.buttonColors(containerColor = InstacartGreen),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("Add", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
