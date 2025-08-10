package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val UploadFileIcon: ImageVector
    get() {
        if (_UploadFileIcon != null) {
            return _UploadFileIcon!!
        }
        _UploadFileIcon = ImageVector.Builder(
            name = "UploadFileIcon",
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
                curveTo(39f, 40.758f, 36.756f, 43f, 34f, 43f)
                horizontalLineTo(14f)
                curveTo(11.244f, 43f, 9f, 40.758f, 9f, 38f)
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
                        0.5f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(19f, 19.063f),
                    end = Offset(26.656f, 35.396f)
                )
            ) {
                moveTo(23f, 35f)
                curveTo(23f, 35.552f, 23.448f, 36f, 24f, 36f)
                curveTo(24.552f, 36f, 25f, 35.552f, 25f, 35f)
                horizontalLineTo(24f)
                horizontalLineTo(23f)
                close()
                moveTo(24.707f, 19.293f)
                curveTo(24.317f, 18.902f, 23.683f, 18.902f, 23.293f, 19.293f)
                lineTo(16.929f, 25.657f)
                curveTo(16.538f, 26.047f, 16.538f, 26.681f, 16.929f, 27.071f)
                curveTo(17.319f, 27.462f, 17.953f, 27.462f, 18.343f, 27.071f)
                lineTo(24f, 21.414f)
                lineTo(29.657f, 27.071f)
                curveTo(30.047f, 27.462f, 30.681f, 27.462f, 31.071f, 27.071f)
                curveTo(31.462f, 26.681f, 31.462f, 26.047f, 31.071f, 25.657f)
                lineTo(24.707f, 19.293f)
                close()
                moveTo(24f, 35f)
                horizontalLineTo(25f)
                lineTo(25f, 20f)
                horizontalLineTo(24f)
                horizontalLineTo(23f)
                lineTo(23f, 35f)
                horizontalLineTo(24f)
                close()
            }
        }.build()

        return _UploadFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _UploadFileIcon: ImageVector? = null
