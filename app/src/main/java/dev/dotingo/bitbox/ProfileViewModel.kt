package dev.dotingo.bitbox

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.bitbox.model.AuthApi
import dev.dotingo.bitbox.model.StorageWithOwner
import dev.dotingo.bitbox.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val api: AuthApi) : ViewModel() {

    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile

    private val _storages = MutableStateFlow<List<StorageWithOwner>>(emptyList())
    val storages: StateFlow<List<StorageWithOwner>> = _storages

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadProfile() {
        viewModelScope.launch {
            try {
                val response = api.getProfile()
                if (response.isSuccessful) {
                    _profile.value = response.body()
                    getUserStorages(_profile.value?.id ?: "")
                } else {
                    Log.e("AuthViewModel", "Ошибка профиля: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Ошибка запроса профиля", e)
            }
        }
    }

    private fun getUserStorages(userId: String) {
        viewModelScope.launch {
            try {
                Log.d("ProfileViewModel", "Запрашиваю хранилища для userId=$userId")
                val response = api.getUserStorages(userId)
                if (response.isSuccessful) {
                    val storagesList = response.body() ?: emptyList()
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
                    Log.d("ProfileViewModel", "${e.localizedMessage}")
            } finally {

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