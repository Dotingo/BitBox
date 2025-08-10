package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ApkFileIcon: ImageVector
    get() {
        if (_ApkFileIcon != null) {
            return _ApkFileIcon!!
        }
        _ApkFileIcon = ImageVector.Builder(
            name = "ApkFileIcon",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        1f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(5.636f, 9.878f),
                    end = Offset(38.122f, 42.364f)
                )
            ) {
                moveTo(8f, 38f)
                verticalLineTo(10f)
                curveTo(8f, 6.686f, 10.686f, 4f, 14f, 4f)
                horizontalLineTo(28f)
                lineTo(40f, 16f)
                verticalLineTo(38f)
                curveTo(40f, 41.314f, 37.314f, 44f, 34f, 44f)
                horizontalLineTo(14f)
                curveTo(10.686f, 44f, 8f, 41.314f, 8f, 38f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        0.493f to Color.White.copy(alpha = 0f),
                        0.997f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(5.636f, 9.878f),
                    end = Offset(38.122f, 42.364f)
                )
            ) {
                moveTo(27.586f, 5f)
                lineTo(39f, 16.414f)
                verticalLineTo(38f)
                curveTo(39f, 40.756f, 36.756f, 43f, 34f, 43f)
                horizontalLineTo(14f)
                curveTo(11.244f, 43f, 9f, 40.756f, 9f, 38f)
                verticalLineTo(10f)
                curveTo(9f, 7.242f, 11.244f, 5f, 14f, 5f)
                horizontalLineTo(27.586f)
                close()
                moveTo(28f, 4f)
                horizontalLineTo(14f)
                curveTo(10.686f, 4f, 8f, 6.686f, 8f, 10f)
                verticalLineTo(38f)
                curveTo(8f, 41.314f, 10.686f, 44f, 14f, 44f)
                horizontalLineTo(34f)
                curveTo(37.314f, 44f, 40f, 41.314f, 40f, 38f)
                verticalLineTo(16f)
                lineTo(28f, 4f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.69803923f),
                        0.519f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(25.586f, 6.414f),
                    end = Offset(37.586f, 18.414f)
                )
            ) {
                moveTo(28f, 12f)
                verticalLineTo(4f)
                lineTo(40f, 16f)
                horizontalLineTo(32f)
                curveTo(29.79f, 16f, 28f, 14.21f, 28f, 12f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.69803923f),
                        0.52f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(19.5f, 25f),
                    end = Offset(33f, 34.5f)
                )
            ) {
                moveTo(29.157f, 23.07f)
                curveTo(29.498f, 22.479f, 30.253f, 22.277f, 30.844f, 22.618f)
                curveTo(31.434f, 22.958f, 31.637f, 23.713f, 31.296f, 24.304f)
                lineTo(29.905f, 26.71f)
                curveTo(32.794f, 28.536f, 34.754f, 31.655f, 34.934f, 35.234f)
                horizontalLineTo(13f)
                curveTo(13.179f, 31.662f, 15.132f, 28.547f, 18.012f, 26.72f)
                lineTo(16.617f, 24.304f)
                curveTo(16.276f, 23.713f, 16.479f, 22.958f, 17.069f, 22.618f)
                curveTo(17.66f, 22.277f, 18.415f, 22.479f, 18.756f, 23.07f)
                lineTo(20.238f, 25.637f)
                curveTo(21.402f, 25.225f, 22.658f, 25f, 23.967f, 25f)
                curveTo(25.269f, 25f, 26.518f, 25.223f, 27.677f, 25.631f)
                lineTo(29.157f, 23.07f)
                close()
                moveTo(19.44f, 30.185f)
                curveTo(18.797f, 29.789f, 17.851f, 30.05f, 17.331f, 30.776f)
                curveTo(16.812f, 31.501f, 16.916f, 32.42f, 17.56f, 32.816f)
                curveTo(18.204f, 33.212f, 19.15f, 32.95f, 19.669f, 32.225f)
                curveTo(20.188f, 31.5f, 20.084f, 30.582f, 19.44f, 30.185f)
                close()
                moveTo(30.669f, 30.776f)
                curveTo(30.149f, 30.05f, 29.203f, 29.789f, 28.56f, 30.185f)
                curveTo(27.916f, 30.582f, 27.812f, 31.5f, 28.331f, 32.225f)
                curveTo(28.85f, 32.95f, 29.796f, 33.212f, 30.44f, 32.816f)
                curveTo(31.084f, 32.42f, 31.188f, 31.501f, 30.669f, 30.776f)
                close()
            }
        }.build()

        return _ApkFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ApkFileIcon: ImageVector? = null
