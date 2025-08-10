package dev.dotingo.bitbox.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dotingo.bitbox.EntityViewModel
import dev.dotingo.bitbox.model.Entities
import dev.dotingo.bitbox.model.StorageWithOwner
import dev.dotingo.bitbox.storageProgressColor
import dev.dotingo.bitbox.ui.icons.ApkFileIcon
import dev.dotingo.bitbox.ui.icons.ArchiveFileIcon
import dev.dotingo.bitbox.ui.icons.AudioFileIcon
import dev.dotingo.bitbox.ui.icons.CodeFileIcon
import dev.dotingo.bitbox.ui.icons.CreateFolderIcon
import dev.dotingo.bitbox.ui.icons.FileIcon
import dev.dotingo.bitbox.ui.icons.FolderIcon
import dev.dotingo.bitbox.ui.icons.ImageFileIcon
import dev.dotingo.bitbox.ui.icons.SvgFileIcon
import dev.dotingo.bitbox.ui.icons.TextFileIcon
import dev.dotingo.bitbox.ui.icons.UploadFileIcon
import dev.dotingo.bitbox.ui.icons.UploadFolderIcon
import dev.dotingo.bitbox.ui.icons.VideoFileIcon
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun EntitiesScreen(
    modifier: Modifier = Modifier,
    viewModel: EntityViewModel = hiltViewModel(),
    storageId: String,
    storageName: String,
    navigateBack: () -> Unit
) {
    val storage by viewModel.storage.collectAsStateWithLifecycle()
    val entities by viewModel.entities.collectAsStateWithLifecycle()
    val userLogins by viewModel.userLogins.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadStorage(storageId)
        viewModel.loadEntities(storageId)
    }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments()
    ) { uris ->
        val files = uris.mapNotNull { uriToFile(context, it) }
        if (files.isNotEmpty()) {
            viewModel.uploadFiles(storageId, "", files)
        }
    }

    val treeLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { treeUri ->
        if (treeUri != null) {
            try {
                context.contentResolver.takePersistableUriPermission(
                    treeUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: Exception) {
            }
            coroutineScope.launch {

            }
        }
    }

    var showCreateDirDialog by remember { mutableStateOf(false) }
    var newDirName by remember { mutableStateOf("") }

    if (showSheet && storage != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
        ) {
            StorageInfoContent(storage = storage!!)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            topBar = {
                TopAppBar(
                    title = { Text(storageName) },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                showSheet = true
                                sheetState.show()
                            }
                        }) {
                            Icon(Icons.Default.Info, "")
                        }
                    }
                )
            },
            floatingActionButton = {}
        ) { paddingValues ->
            if (showCreateDirDialog) {
                AlertDialog(
                    onDismissRequest = { showCreateDirDialog = false },
                    title = { Text("Создать папку") },
                    text = {
                        OutlinedTextField(
                            value = newDirName,
                            onValueChange = { newDirName = it },
                            label = { Text("Имя директории") },
                            singleLine = true
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            val name = newDirName.trim()
                            if (name.isNotEmpty()) {

                            }
                            newDirName = ""
                            showCreateDirDialog = false
                        }) { Text("Создать") }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            newDirName = ""
                            showCreateDirDialog = false
                        }) { Text("Отмена") }
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                val used = storage?.storage?.used?.toFloat() ?: 0f
                val total = storage?.storage?.size?.toFloat() ?: 0f
                val progress = if (total > 0) (used / total).coerceIn(0f, 1f) else 0f
                val progressColor = storageProgressColor(progress)

                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = progressColor,
                    progress = { progress },
                )
                LazyColumn(
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    items(entities) { entity ->
                        EntityItem(entity = entity, uploaderLogin = userLogins[entity.uploader])
                    }
                }
            }
        }

        ExpandableFab(
            modifier = Modifier.fillMaxSize(),
            onCreateDirectory = { showCreateDirDialog = true },
            onUploadFiles = { fileLauncher.launch(arrayOf("*/*")) },
            onUploadDirectory = { treeLauncher.launch(null) }
        )
    }
}

@Composable
fun StorageInfoContent(storage: StorageWithOwner) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Информация о хранилище", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        ListItem(
            overlineContent = { Text("Название") },
            headlineContent = { Text(storage.storage.name) },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        ListItem(
            overlineContent  = { Text("Владелец") },
            headlineContent = { Text(storage.ownerLogin ?: "—") },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        ListItem(
            overlineContent  = { Text("Описание") },
            headlineContent = { Text(storage.storage.description ?: "—") },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        HorizontalDivider()
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ListItem(
                overlineContent  = { Text("Занято") },
                headlineContent = { Text(formatSize(storage.storage.used)) },
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = ListItemDefaults.colors(containerColor = Color.Transparent)
            )
            ListItem(
                overlineContent = { Text("Размер") },
                headlineContent = { Text(formatSize(storage.storage.size)) },
                colors = ListItemDefaults.colors(containerColor = Color.Transparent)
            )
        }
        val maxSizeText = if (storage.storage.restrictFileSize) {
            formatSize(storage.storage.maxFileSize.toLong())
        } else {
            "—"
        }
        val maxCountText = if (storage.storage.restrictFilesCount) {
            formatSize(storage.storage.maxFileSize.toLong())
        } else {
            "—"
        }
        ListItem(
            overlineContent = { Text("Максимально допустимый размер файла") },
            headlineContent = { Text(maxSizeText) },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        ListItem(
            overlineContent = { Text("Максимально допустимое количество файлов") },
            headlineContent = { Text(maxCountText) },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        val access = when(storage.storage.access) {
            "private" -> "Приватное"
            "public" -> "Публичное"
            else -> "—"
        }
        ListItem(
            overlineContent = { Text("Доступ") },
            headlineContent = { Text(access) },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        ListItem(
            overlineContent = { Text("Дата и время создания") },
            headlineContent = { Text(formatDateTime(storage.storage.createdAt)) },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
        HorizontalDivider()
        ListItem(
            overlineContent = { Text("Участники") },
            headlineContent = { Text("${storage.storage.members.size}") },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableFab(
    modifier: Modifier = Modifier,
    onCreateDirectory: () -> Unit,
    onUploadFiles: () -> Unit,
    onUploadDirectory: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    // анимируем угол поворота и масштаб иконки
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 45f else 0f,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val scale by animateFloatAsState(
        targetValue = if (expanded) 0.95f else 1f,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val iconAlpha by animateFloatAsState(
        targetValue = if (expanded) 1f else 1f, // можно варьировать, сейчас всегда 1
        animationSpec = tween(250)
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { expanded = false }
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(end = 32.dp, bottom = 32.dp)
        ) {
            val items = listOf(
                Triple("Загрузить файлы", UploadFileIcon) { onUploadFiles() },
                Triple("Загрузить папку", UploadFolderIcon) { onUploadDirectory() },
                Triple("Создать папку", CreateFolderIcon) { onCreateDirectory() }
            )

            items.reversed().forEachIndexed { index, (label, icon, action) ->
                AnimatedVisibility(
                    visible = expanded,
                    enter = scaleIn() + fadeIn() + slideInVertically { it / 2 } + expandVertically(),
                    exit = scaleOut() + fadeOut() + shrinkVertically()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 3.dp)
                    ) {
                        SmallFloatingActionButton(
                            onClick = {
                                action()
                                expanded = false
                            },
                            containerColor = Color(0xFF145732)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .padding(end = 5.dp)
                                )
                                Text(
                                    text = label,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = { expanded = !expanded },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = if (expanded) "Закрыть" else "Открыть",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .graphicsLayer {
                            rotationZ = rotation
                            scaleX = scale
                            scaleY = scale
                            alpha = iconAlpha
                        }
                )
            }
        }
    }

    BackHandler(enabled = expanded) { expanded = false }
}

@Composable
fun EntityItem(entity: Entities, uploaderLogin: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = getIconForEntity(entity),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))

                Column {
                    Text(
                        text = entity.fullname ?: "",
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        "${formatDate(entity.uploadedAt)} • $uploaderLogin • ${formatSize(entity.size ?: 0)}",
                        color = Color(0xFFDCDBDC).copy(0.8f),
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun getIconForEntity(entity: Entities): ImageVector {
    return if (entity.type == "file") {
        when (entity.fullname?.substringAfterLast(".")?.lowercase()) {
            "java", "kt", "kts", "py", "js", "ts", "html", "css", "xml", "json", "yaml", "yml", "c", "cpp", "h", "hpp", "sh", "bat", "cmd" -> CodeFileIcon
            "mp3", "wav", "flac", "ogg", "aac", "m4a" -> AudioFileIcon
            "mp4", "mkv", "avi", "mov", "webm", "wmv" -> VideoFileIcon
            "jpg", "jpeg", "png", "webp", "bmp" -> ImageFileIcon
            "zip", "rar", "7z", "tar", "gz" -> ArchiveFileIcon
            "txt" -> TextFileIcon
            "svg" -> SvgFileIcon
            "apk" -> ApkFileIcon
            else -> FileIcon
        }
    } else {
        FolderIcon
    }
}

fun uriToFile(context: Context, uri: Uri): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val fileName = getFileNameFromUri(context, uri)
        val tempFile = File(context.cacheDir, fileName ?: "")
        tempFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var result: String? = null
    if (uri.scheme == "content") {
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index >= 0) {
                    result = cursor.getString(index)
                }
            }
        }
    }
    if (result == null) {
        result = uri.path
        val cut = result?.lastIndexOf('/')
        if (cut != null && cut != -1) {
            result = result.substring(cut + 1)
        }
    }
    return result
}