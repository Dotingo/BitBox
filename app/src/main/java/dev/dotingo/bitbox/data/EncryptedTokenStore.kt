package dev.dotingo.bitbox.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.encryptedDataStore by preferencesDataStore(
    name = "secure_prefs"
)

class EncryptedTokenStore(private val context: Context) {

    companion object {
        private val COOKIE_KEY = stringPreferencesKey("cookie")
    }

    val cookieFlow: Flow<String?> = context.encryptedDataStore.data
        .map { it[COOKIE_KEY] }

    suspend fun saveCookie(cookie: String) {
        context.encryptedDataStore.edit {
            it[COOKIE_KEY] = cookie
        }
    }

    suspend fun clearCookie() {
        context.encryptedDataStore.edit {
            it.remove(COOKIE_KEY)
        }
    }
}