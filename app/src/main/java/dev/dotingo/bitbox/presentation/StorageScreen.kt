package dev.dotingo.bitbox.presentation


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.bitbox.StorageViewModel
import dev.dotingo.bitbox.model.StorageWithOwner
import dev.dotingo.bitbox.storageProgressColor
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.math.ln
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageScreen(
    viewModel: StorageViewModel = hiltViewModel(),
    navigateToEntitiesScreen: (String, String) -> Unit,
    navigateToProfileScreen: () -> Unit
) {

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
                    IconButton(onClick = { navigateToProfileScreen() })
                    {
                        Icon(
                            Icons.Default.AccountCircle,
                            "Account"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
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
                        contentPadding = PaddingValues(bottom = 60.dp),
                        modifier = Modifier.navigationBarsPadding()
                    ) {
                        items(storages) { storage ->
                            val used = storage.storage.used.toFloat()
                            val total = storage.storage.size.toFloat()
                            val progress = if (total > 0) (used / total).coerceIn(0f, 1f) else 0f

                            StorageCard(storage, progress) { storageId, storageName ->
                                navigateToEntitiesScreen(storageId, storageName)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StorageCard(
    storage: StorageWithOwner,
    progress: Float,
    navigateToEntitiesScreen: (String, String) -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff332633)
        ), onClick = {
            navigateToEntitiesScreen(storage.storage._id, storage.storage.name)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
        ) {
            Text(
                storage.storage.name,
                color = Color(0xFFDCDBDC),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 10.dp)
            )

            Text(
                text = "${formatSize(storage.storage.used)} / ${
                    formatSize(
                        storage.storage.size
                    )
                }",
                color = Color(0xFFDCDBDC),
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .align(Alignment.Center)
            )
            Text(
                "${formatDate(storage.storage.createdAt)} • ${storage.ownerLogin}",
                color = Color(0xFFDCDBDC).copy(0.8f),
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            StorageCircularProgress(
                progress, modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }
    }
}

@Composable
fun StorageCircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Dp = 70.dp
) {
    val percent = (progress * 100).coerceIn(0f, 100f)

    val progressColor = storageProgressColor(progress)

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // Фон круга
        Canvas(modifier = Modifier.matchParentSize()) {
            drawArc(
                color = Color.DarkGray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 12f, cap = StrokeCap.Round)
            )

            // Прогресс
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = 360f * (progress.coerceIn(0f, 1f)),
                useCenter = false,
                style = Stroke(width = 12f, cap = StrokeCap.Round)
            )
        }

        // Текст с %
        Text(
            text = "${percent.toInt()}%",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


fun formatSize(bytes: Long): String {
    if (bytes < 1024) return "${bytes}Б"
    val exp = (ln(bytes.toDouble()) / ln(1024.0)).toInt()
    val units = arrayOf("Б", "КБ", "МБ", "ГБ", "ТБ", "ПБ")
    val value = bytes / 1024.0.pow(exp.toDouble())
    return String.format("%.1f%s", value, units[exp])
}

fun formatDateTime(isoTime: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
    outputFormat.timeZone = TimeZone.getDefault()

    val date = inputFormat.parse(isoTime)!!
    return outputFormat.format(date)
}
