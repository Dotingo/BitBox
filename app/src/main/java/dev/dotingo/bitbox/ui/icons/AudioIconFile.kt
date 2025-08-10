package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AudioFileIcon: ImageVector
    get() {
        if (_AudioFileIcon != null) {
            return _AudioFileIcon!!
        }
        _AudioFileIcon = ImageVector.Builder(
            name = "VideoFileIcon",
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
                    start = Offset(19.429f, 20.7f),
                    end = Offset(26.19f, 36.042f)
                )
            ) {
                moveTo(24.217f, 19f)
                curveTo(25.198f, 19f, 26.164f, 19.238f, 27.033f, 19.693f)
                lineTo(30.699f, 21.614f)
                curveTo(31.542f, 22.056f, 32.049f, 22.949f, 31.996f, 23.9f)
                lineTo(31.829f, 26.908f)
                curveTo(31.805f, 27.337f, 31.355f, 27.606f, 30.966f, 27.424f)
                lineTo(27.228f, 25.679f)
                lineTo(25.71f, 25.071f)
                verticalLineTo(32.357f)
                curveTo(25.71f, 34.369f, 23.537f, 36f, 20.855f, 36f)
                curveTo(18.174f, 36f, 16f, 34.369f, 16f, 32.357f)
                curveTo(16f, 30.345f, 18.174f, 28.714f, 20.855f, 28.714f)
                curveTo(21.74f, 28.714f, 22.569f, 28.892f, 23.283f, 29.202f)
                verticalLineTo(19.607f)
                curveTo(23.283f, 19.272f, 23.555f, 19f, 23.89f, 19f)
                horizontalLineTo(24.217f)
                close()
            }
        }.build()

        return _AudioFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AudioFileIcon: ImageVector? = null
