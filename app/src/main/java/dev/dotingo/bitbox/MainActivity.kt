package dev.dotingo.bitbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.dotingo.bitbox.ui.theme.BitBoxTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BitBoxTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen(viewModel: AuthViewModel = hiltViewModel()) {
    var login by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val result by viewModel.registerResult.collectAsStateWithLifecycle()


    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = login, onValueChange = { login = it }, label = { Text("Логин") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Пароль") })
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
    }
}

@Composable
fun LoginScreen(viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val result by viewModel.loginResult.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = {
                viewModel.login(email, password)
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Войти")
        }

        result?.let {
            Text(it, color = if (it.startsWith("Ошибка")) Color.Red else Color.Green)
        }
    }
}
