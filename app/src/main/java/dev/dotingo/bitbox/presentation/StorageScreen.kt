package dev.dotingo.bitbox.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.dotingo.bitbox.StorageViewModel
import kotlin.math.ln
import kotlin.math.pow

@Composable
fun StorageScreen(viewModel: StorageViewModel = hiltViewModel()) {
    val storages by viewModel.storages.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadStorages()
    }

    if (error != null) {
        Text("Ошибка: $error")
    } else {
        LazyColumn(
            modifier = Modifier
                .navigationBarsPadding()
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            items(storages) { storage ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(storage.name, color = Color(0xFFDCDBDC))
                        Text("Dotingo", color = Color(0xFFDCDBDC))
                    }
                    Text(storage.description ?: "", color = Color(0xFFDCDBDC))
                    Text(
                        "${formatSize(storage.used)}/${formatSize(storage.size)}",
                        color = Color(0xFFDCDBDC)
                    )
                }
                HorizontalDivider()
            }
        }
    }
}

fun formatSize(bytes: Long): String {
    if (bytes < 1024) return "$bytes Б"
    val exp = (ln(bytes.toDouble()) / ln(1024.0)).toInt()
    val units = arrayOf("Б", "КБ", "МБ", "ГБ", "ТБ", "ПБ")
    val value = bytes / 1024.0.pow(exp.toDouble())
    return String.format("%.2f %s", value, units[exp])
}
