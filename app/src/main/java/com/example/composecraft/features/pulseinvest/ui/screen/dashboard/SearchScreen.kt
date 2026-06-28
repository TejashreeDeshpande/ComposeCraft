package com.example.composecraft.features.pulseinvest.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.StockViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SearchScreen(vm: StockViewModel, onBack: () -> Unit, onStockClick: (String) -> Unit) {
    val query by vm.searchQuery.collectAsState()
    val results by vm.searchResults.collectAsState()

    PulseScaffold(title = "Search", showBack = true, onBack = onBack) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(8.dp))
            PulseTextField(query, vm::onSearchQueryChange, "Search stocks, ETFs, categories...")
            Spacer(Modifier.height(16.dp))
            if (query.isBlank()) {
                Text("RECENT SEARCHES", color = PulseTextMuted, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                val defaults = listOf("NVDA" to "🟢", "AAPL" to "🍎", "MSFT" to "🔷", "TSLA" to "⚡")
                defaults.forEach { (sym, emoji) ->
                    StockRow(emoji, sym, "Technology · NASDAQ", "--", "--", true) { onStockClick(sym) }
                }
                Spacer(Modifier.height(16.dp))
                Text("TRENDING", color = PulseTextMuted, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                listOf("🔥 Artificial Intelligence","📈 Top ETFs","💻 Technology Stocks","₿ Cryptocurrencies")
                    .forEach { Text(it, color = PulseTextSecondary, fontSize = 13.sp, modifier = Modifier.padding(vertical = 6.dp)) }
            } else {
                LazyColumn {
                    items(results) { stock ->
                        StockRow("🟢", stock.symbol, stock.name,
                            "${"$%.2f".format(stock.price)}",
                            "${if (stock.isPositive) "+" else ""}${"%.2f".format(stock.changePercent)}%",
                            stock.isPositive
                        ) { onStockClick(stock.symbol) }
                    }
                    if (results.isEmpty()) item {
                        Box(Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                            Text("No results for \"$query\"", color = PulseTextSecondary, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
