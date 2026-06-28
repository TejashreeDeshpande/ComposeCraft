package com.example.composecraft.features.pulseinvest.ui.screen.funds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.FundsViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun DepositFundsScreen(vm: FundsViewModel, onBack: () -> Unit) {
    val state by vm.state.collectAsState()
    LaunchedEffect(state.isSuccess) { if (state.isSuccess) { vm.resetSuccess(); onBack() } }

    PulseScaffold(title = "Deposit Funds", showBack = true, onBack = onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize().background(PulseDark)
                .padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Choose Method", color = PulseTextSecondary, fontSize = 14.sp)

            listOf(
                Triple("🏦", "Bank Transfer",  "ACH transfer from your bank"),
                Triple("💳", "Debit Card",     "Instant deposit via debit card"),
                Triple("🔁", "Wire Transfer",  "Transfer funds via wire")
            ).forEach { (emoji, title, desc) ->
                MethodCard(emoji, title, desc, state.selectedMethod == title) { vm.onMethodChange(title) }
            }

            Text("Amount", color = PulseTextSecondary, fontSize = 14.sp)

            OutlinedTextField(
                value = state.amount,
                onValueChange = vm::onAmountChange,
                placeholder = { Text("0.00", color = PulseTextMuted) },
                prefix = {
                    Text("$", color = PulseGreen, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(
                    color = PulseGreen, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PulseGreen,
                    unfocusedBorderColor = PulseBorder,
                    focusedContainerColor = PulseSurface,
                    unfocusedContainerColor = PulseSurface
                ),
                shape = RoundedCornerShape(14.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(Modifier.weight(1f))
            state.error?.let { Text(it, color = PulseRed, fontSize = 13.sp) }
            PulseButton("Continue", onClick = vm::deposit, isLoading = state.isLoading)
        }
    }
}
