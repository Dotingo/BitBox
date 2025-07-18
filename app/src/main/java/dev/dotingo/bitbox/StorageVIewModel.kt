package dev.dotingo.bitbox

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.bitbox.model.AuthApi
import dev.dotingo.bitbox.model.Storage
import dev.dotingo.bitbox.model.StorageWithOwner
import dev.dotingo.bitbox.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val api: AuthApi // или StorageApi, если разделено
) : ViewModel() {

    private val _storages = MutableStateFlow<List<StorageWithOwner>>(emptyList())
    val storages: StateFlow<List<StorageWithOwner>> = _storages

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    fun loadStorages() {
        viewModelScope.launch {
            _isRefreshing.value = true  // 🔹 запускаем индикатор

            try {
                val response = api.getStorages()
                if (response.isSuccessful) {
                    val storagesList = response.body() ?: emptyList()

                    // загружаем владельцев параллельно
                    val storagesWithOwners = storagesList.map { storage ->
                        val ownerLogin = getUserLoginById(storage.owner)
                        StorageWithOwner(storage, ownerLogin)
                    }

                    _storages.value = storagesWithOwners
                    _error.value = null
                } else {
                    _error.value = "Ошибка: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.localizedMessage ?: "Неизвестная ошибка"}"
            } finally {
                _isRefreshing.value = false // 🔹 выключаем индикатор
            }
        }
    }

    private suspend fun getUserLoginById(userId: String): String? {
        return try {
            val response = api.getUserById(userId)
            if (response.isSuccessful) {
                response.body()?.login
            } else null
        } catch (e: Exception) {
            Log.e("StorageViewModel", "Ошибка загрузки пользователя", e)
            null
        }
    }
}
