package dev.dotingo.bitbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.dotingo.bitbox.data.EncryptedTokenStore
import dev.dotingo.bitbox.navigation.LoginScreenNav
import dev.dotingo.bitbox.navigation.StorageScreenNav
import dev.dotingo.bitbox.navigation.TopAppNavHost
import dev.dotingo.bitbox.ui.theme.BitBoxTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tokenStore: EncryptedTokenStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val isLoggedIn = tokenStore.isUserLoggedIn()
            enableEdgeToEdge()
            setContent {
                BitBoxTheme {
                    TopAppNavHost(startDestination = if (isLoggedIn) StorageScreenNav else LoginScreenNav)
                }
            }
        }
    }
}
