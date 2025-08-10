package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ImageFileIcon: ImageVector
    get() {
        if (_ImageFileIcon != null) {
            return _ImageFileIcon!!
        }
        _ImageFileIcon = ImageVector.Builder(
            name = "ImageFileIcon",
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
                curveTo(39f, 40.756f, 36.758f, 43f, 34f, 43f)
                horizontalLineTo(14f)
                curveTo(11.244f, 43f, 9f, 40.756f, 9f, 38f)
                verticalLineTo(10f)
                curveTo(9f, 7.244f, 11.244f, 5f, 14f, 5f)
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
                        0.519f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(26.878f, 20.878f),
                    end = Offset(31.122f, 25.122f)
                )
            ) {
                moveTo(29f, 26f)
                curveTo(30.657f, 26f, 32f, 24.657f, 32f, 23f)
                curveTo(32f, 21.343f, 30.657f, 20f, 29f, 20f)
                curveTo(27.343f, 20f, 26f, 21.343f, 26f, 23f)
                curveTo(26f, 24.657f, 27.343f, 26f, 29f, 26f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.69803923f),
                        0.519f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(16.734f, 27.586f),
                    end = Offset(30.21f, 41.06f)
                )
            ) {
                moveTo(14f, 36.75f)
                curveTo(14f, 37.434f, 14.56f, 38f, 15.25f, 38f)
                horizontalLineTo(32.75f)
                curveTo(33.44f, 38f, 34f, 37.444f, 34f, 36.75f)
                curveTo(34f, 35.046f, 33.056f, 30f, 31f, 30f)
                curveTo(29.986f, 30f, 28.41f, 32f, 27f, 32f)
                curveTo(24.2f, 32f, 21.5f, 26f, 19f, 26f)
                curveTo(16.5f, 26f, 14f, 33.452f, 14f, 36.75f)
                close()
            }
        }.build()

        return _ImageFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ImageFileIcon: ImageVector? = null
