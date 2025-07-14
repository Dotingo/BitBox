package dev.dotingo.bitbox.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.bitbox.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navigateToMainScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val result by viewModel.registerResult.collectAsStateWithLifecycle()

    val authSuccess by viewModel.authSuccess.collectAsState()

    LaunchedEffect(authSuccess) {
        if (authSuccess) {
            navigateToMainScreen()
        }
    }

    Column(modifier = Modifier.navigationBarsPadding().statusBarsPadding().padding(16.dp)) {
        OutlinedTextField(value = login, onValueChange = { login = it }, label = { Text("Логин") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") })
        Button(
            onClick = {
                viewModel.register(login, email, password)
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Зарегистрироваться")
        }

        result?.let {
            Text(it, color = if (it.startsWith("Ошибка")) Color.Red else Color.Green)
        }

        Button(navigateToLoginScreen) {
            Text("На экран входа")
        }

    }
}