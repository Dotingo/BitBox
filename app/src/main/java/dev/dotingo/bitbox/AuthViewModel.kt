package dev.dotingo.bitbox


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.bitbox.data.EncryptedTokenStore
import dev.dotingo.bitbox.model.ApiClient
import dev.dotingo.bitbox.model.AuthApi
import dev.dotingo.bitbox.model.LoginRequest
import dev.dotingo.bitbox.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenStore: EncryptedTokenStore,
    private val authApi: AuthApi
) : ViewModel() {

    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess: StateFlow<Boolean> = _authSuccess

    fun register(login: String, email: String, password: String, role: String = "user") {
        viewModelScope.launch {
            try {
                val request = RegisterRequest(login, email, password, role)
                val response = authApi.register(request)

                if (response.isSuccessful) {
                    val cookies = response.headers().values("Set-Cookie")
                    val accessToken = cookies
                        .find { it.startsWith("access=") }
                        ?.substringBefore(";")
                    val refreshToken = cookies
                        .find { it.startsWith("refresh=") }
                        ?.substringBefore(";")

                    if (!accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                        // Сохраняем оба токена одной строкой через "; "
                        tokenStore.saveCookie("$accessToken; $refreshToken")
                        _registerResult.value = "Успешная регистрация"
                        _authSuccess.value = true
                    } else {
                        _registerResult.value = "Ошибка: токены не найдены"
                    }
                } else {
                    val error = response.errorBody()?.string()
                    _registerResult.value = "Ошибка: $error"
                }
            } catch (e: Exception) {
                _registerResult.value = "Ошибка: ${e.localizedMessage}"
            }
        }
    }

    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authApi.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val cookies = response.headers().values("Set-Cookie")
                    // Ищем именно accessToken и refreshToken
                    val access = cookies
                        .find { it.startsWith("access") }
                        ?.substringBefore(";")
                    val refresh = cookies
                        .find { it.startsWith("refresh=") }
                        ?.substringBefore(";")

                    if (!access.isNullOrBlank() && !refresh.isNullOrBlank()) {
                        tokenStore.saveCookie("$access; $refresh")
                        _authSuccess.value = true
                        _loginResult.value = "Вход успешен"
                    } else {
                        _loginResult.value = "Ошибка входа: токены не найдены"
                    }
                } else {
                    _loginResult.value = "Ошибка входа: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _loginResult.value = "Ошибка: ${e.localizedMessage}"
            }
        }
    }
}



