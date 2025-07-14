package dev.dotingo.bitbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.bitbox.model.AuthApi
import dev.dotingo.bitbox.model.Storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val api: AuthApi // или StorageApi, если разделено
) : ViewModel() {

    private val _storages = MutableStateFlow<List<Storage>>(emptyList())
    val storages: StateFlow<List<Storage>> = _storages

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadStorages() {
        viewModelScope.launch {
            try {
                val response = api.getStorages()
                if (response.isSuccessful) {
                    _storages.value = response.body() ?: emptyList()
                } else {
                    _error.value = "Ошибка: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.localizedMessage}"
            }
        }
    }
}
