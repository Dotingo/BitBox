package dev.dotingo.bitbox


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

    fun register(login: String, email: String, password: String, role: String = "user") {
        viewModelScope.launch {
            try {
                val request = RegisterRequest(login, email, password, role)
                val response = ApiClient.authApi.register(request)

                if (response.isSuccessful) {
                    val cookies = response.headers()["Set-Cookie"]
                    _registerResult.value = if (cookies != null) {
                        "Успешная регистрация. Cookie: $cookies"
                    } else {
                        "Успешная регистрация. Cookie не найден."
                    }
                    // Можно сохранить cookie токены, если нужно
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
                    val cookie = response.headers()["Set-Cookie"]
                    if (!cookie.isNullOrBlank()) {
                        tokenStore.saveCookie(cookie)
                        _loginResult.value = "Вход успешен"
                    } else {
                        _loginResult.value = "Cookie не найден"
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


