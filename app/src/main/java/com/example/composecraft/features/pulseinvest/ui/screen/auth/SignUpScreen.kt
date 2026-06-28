package com.example.composecraft.features.pulseinvest.ui.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.AuthViewModel
import com.example.composecraft.features.pulseinvest.ui.components.*
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SignUpScreen(vm: AuthViewModel, onSuccess: (String) -> Unit, onLogin: () -> Unit) {
    val state by vm.state.collectAsState()
    LaunchedEffect(state.isSuccess) { if (state.isSuccess) { vm.resetSuccess(); onSuccess(state.email) } }

    Box(modifier = Modifier.fillMaxSize().background(PulseDark)) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(8.dp))
            Text("Create Account", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = PulseTextPrimary)
            Column {
                Text("Full Name", color = PulseTextSecondary, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))
                PulseTextField(state.name, vm::onNameChange, "Tejashree Patil")
            }
            Column {
                Text("Email", color = PulseTextSecondary, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))
                PulseTextField(state.email, vm::onEmailChange, "you@email.com")
            }
            Column {
                Text("Password", color = PulseTextSecondary, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))
                PulseTextField(state.password, vm::onPasswordChange, "••••••••", isPassword = true)
            }
            Column {
                Text("Confirm Password", color = PulseTextSecondary, fontSize = 12.sp)
                Spacer(Modifier.height(4.dp))
                PulseTextField(state.confirmPassword, vm::onConfirmPasswordChange, "••••••••", isPassword = true)
            }
            state.error?.let { Text(it, color = PulseRed, fontSize = 13.sp) }
            Spacer(Modifier.height(8.dp))
            PulseButton("Sign Up", onClick = vm::signUp, isLoading = state.isLoading)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text("Already have an account? ", color = PulseTextSecondary, fontSize = 13.sp)
                TextButton(onClick = onLogin, contentPadding = PaddingValues(0.dp)) {
                    Text("Log In", color = PulseGreen, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
