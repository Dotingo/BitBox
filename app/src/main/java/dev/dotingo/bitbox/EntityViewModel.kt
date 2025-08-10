package dev.dotingo.bitbox

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dotingo.bitbox.model.AuthApi
import dev.dotingo.bitbox.model.Entities
import dev.dotingo.bitbox.model.StorageWithOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EntityViewModel @Inject constructor(private val api: AuthApi) : ViewModel() {

    private val _storage = MutableStateFlow<StorageWithOwner?>(null)
    val storage: StateFlow<StorageWithOwner?> = _storage

    private val _entities = MutableStateFlow<List<Entities>>(emptyList())
    val entities: StateFlow<List<Entities>> = _entities

    private val _userLogins = MutableStateFlow<Map<String, String>>(emptyMap())
    val userLogins: StateFlow<Map<String, String>> = _userLogins

    fun loadStorage(storageId: String) {
        viewModelScope.launch {
            try {
                val response = api.getStorageById(storageId)
                if (response.isSuccessful) {
                    val storage = response.body()

                    if (storage != null) {
                        val ownerLogin = getUserLoginById(storage.owner)
                        val storageWithOwner = StorageWithOwner(storage, ownerLogin)
                        _storage.value = storageWithOwner
                    } else {
                        Log.e("EntityViewModel", "Storage is null")
                    }
                } else {
                    Log.e(
                        "EntityViewModel",
                        "Ошибка загрузки сущностей: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("EntityViewModel", "Ошибка при загрузке хранилища", e)
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

    fun loadEntities(storageId: String) {
        viewModelScope.launch {
            try {
                val response = api.getEntities(storageId)
                if (response.isSuccessful) {
                    val entities = response.body()?.items ?: emptyList()
                    _entities.value = entities

                    entities.mapNotNull { it.uploader }.distinct().forEach { userId ->
                        loadUserLogin(userId)
                    }

                } else {
                    Log.e(
                        "EntityViewModel",
                        "Ошибка загрузки сущностей: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("EntityViewModel", "Ошибка запроса сущностей", e)
            }
        }
    }

    private fun loadUserLogin(userId: String) {
        if (_userLogins.value.containsKey(userId)) return

        viewModelScope.launch {
            try {
                val response = api.getUserById(userId)
                if (response.isSuccessful) {
                    val login = response.body()?.login
                    if (login != null) {
                        _userLogins.update { it + (userId to login) }
                    }
                }
            } catch (e: Exception) {
                Log.e("EntityViewModel", "Ошибка загрузки логина", e)
            }
        }
    }


    fun uploadFiles(storageId: String, parentId: String, files: List<File>) {
        viewModelScope.launch {
            try {
                files.chunked(5).forEach { chunk ->
                    chunk.forEach { file ->

                        val fileBody =
                            file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
                        val entitiesPart =
                            MultipartBody.Part.createFormData("entities", file.name, fileBody)

                        val metadataJson = """{"name":"${file.name}"}"""
                        val metadataBody =
                            metadataJson.toRequestBody("application/json".toMediaTypeOrNull())

                        val parentBody = parentId.toRequestBody("text/plain".toMediaTypeOrNull())

                        val response =
                            api.uploadFile(storageId, entitiesPart, metadataBody, parentBody)
                        if (!response.isSuccessful) {
                            Log.e(
                                "EntityViewModel",
                                "Ошибка загрузки ${file.name}: ${response.errorBody()?.string()}"
                            )
                        } else {
                            Log.d("EntityViewModel", "Файл загружен: ${file.name}")
                        }
                    }
                }
                loadStorage(storageId)
                loadEntities(storageId)
            } catch (e: Exception) {
                Log.e("EntityViewModel", "Ошибка загрузки файлов", e)
            }
        }
    }
}

@Composable
fun storageProgressColor(progress: Float): Color {
    val percent = (progress * 100).coerceIn(0f, 100f)
    return when {
        percent > 85 -> Color.Red
        percent > 60 -> Color(0xFFFFA500) // оранжевый
        else -> MaterialTheme.colorScheme.primary
    }
}