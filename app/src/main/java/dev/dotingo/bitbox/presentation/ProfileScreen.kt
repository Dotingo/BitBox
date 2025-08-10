package dev.dotingo.bitbox.presentation

import android.graphics.BitmapFactory
import android.graphics.drawable.PictureDrawable
import android.util.Base64
import android.view.View
import android.widget.ImageView
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caverock.androidsvg.SVG
import dev.dotingo.bitbox.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val storages by viewModel.storages.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профиль") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() })
                    { Icon(Icons.AutoMirrored.Default.ArrowBack, "") }
                }
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 10.dp), onClick = {}, shape = RoundedCornerShape(10.dp)
            ) {
                Text("Редактировать профиль")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            profile?.avatar?.let { dataUri ->
                val base64 = dataUri.substringAfter("base64,")
                val imageBytes = Base64.decode(base64, Base64.DEFAULT)
                val bitmap = remember(imageBytes) {
                    BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                }

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                } else {
                    val base64 = dataUri.substringAfter("base64,")
                    val svgXml = String(Base64.decode(base64, Base64.DEFAULT))
                    val pictureDrawable by remember(svgXml) {
                        mutableStateOf(
                            PictureDrawable(
                                SVG.getFromString(svgXml).renderToPicture()
                            )
                        )
                    }

                    AndroidView(
                        factory = {
                            ImageView(it).apply {
                                setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                                setImageDrawable(pictureDrawable)
                            }
                        },
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            val hasName = !profile?.name.isNullOrBlank() || !profile?.lastname.isNullOrBlank()

            if (hasName) {
                Text(
                    text = "${profile?.name.orEmpty()} ${profile?.lastname.orEmpty()}",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = profile?.login ?: "",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
            } else {
                Text(
                    text = profile?.login ?: "",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xff332633)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding( 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Email")
                    Text(profile?.email ?: "")
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding( 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Роль")
                    Text(
                        when (profile?.role) {
                            "owner" -> "Владелец"
                            "admin" -> "Администратор"
                            "user" -> "Пользователь"
                            else -> ""
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Дата регистрации")
                    Text(formatDate(profile?.createdAt))
                }

            }
            Text(
                "Список хранилищ",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(
                        Alignment.Start
                    )
                    .padding(top = 10.dp)
            )
            storages.forEach { storage ->
                val used = storage.storage.used.toFloat()
                val total = storage.storage.size.toFloat()
                val progress = if (total > 0) (used / total).coerceIn(0f, 1f) else 0f

                Card(
                    modifier = Modifier.padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xff332633)
                    )
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
        }
    }
}

fun formatDate(isoTime: String?): String {
    if (isoTime.isNullOrBlank()) return "—"

    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()

        val date = inputFormat.parse(isoTime)
        if (date != null) outputFormat.format(date) else "—"
    } catch (e: Exception) {
        e.printStackTrace()
        "—"
    }
}