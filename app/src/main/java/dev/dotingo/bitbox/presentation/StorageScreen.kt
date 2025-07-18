package dev.dotingo.bitbox.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.bitbox.R
import dev.dotingo.bitbox.StorageViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.math.ln
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreen(viewModel: StorageViewModel = hiltViewModel()) {

    val storages by viewModel.storages.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val refreshState = rememberPullToRefreshState()
    val listState = rememberLazyListState()
    val expandedFab by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    LaunchedEffect(Unit) {
        viewModel.loadStorages()
    }

    Scaffold(
        modifier = Modifier.padding(horizontal = 10.dp),
        topBar = {
            TopAppBar(
                title = { Text("Список хранилищ") },

                actions = {
                    IconButton(onClick = {})
                    { Icon(Icons.Default.AccountCircle, "Account") }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                expanded = expandedFab,
                icon = { Icon(Icons.Default.Add, "Add") },
                text = { Text("Создать") })
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            isRefreshing = isRefreshing,
            state = refreshState,
            onRefresh = { viewModel.loadStorages() }
        ) {
            when {
                error != null -> {
                    Text(
                        text = error ?: "Ошибка",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                storages.isEmpty() && !isRefreshing -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Нет данных", color = Color.Gray)
                    }
                }

                else -> {
                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(
                            bottom = 60.dp
                        ),
                        modifier = Modifier
                            .navigationBarsPadding()
                    ) {
                        items(storages) { storage ->
                            Card(
                                modifier = Modifier.padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(
                                        0xff332633
                                    )
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_storage),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(end = 10.dp)
                                    )
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(storage.storage.name, color = Color(0xFFDCDBDC))
                                        Text(
                                            "${formatSize(storage.storage.used)}/${
                                                formatSize(
                                                    storage.storage.size
                                                )
                                            } • ${formatDateTime(storage.storage.createdAt)}",
                                            color = Color(0xFFDCDBDC),
                                            fontSize = 14.sp
                                        )
                                    }
                                    Text(
                                        storage.ownerLogin ?: "",
                                        color = Color(0xFFDCDBDC),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
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

fun formatDateTime(isoTime: String): String {
    // Формат входного времени (ISO 8601)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC") // входное время в UTC

    // Формат для вывода
    val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getDefault() // вывод в локальной зоне

    val date = inputFormat.parse(isoTime)!!
    return outputFormat.format(date)
}
