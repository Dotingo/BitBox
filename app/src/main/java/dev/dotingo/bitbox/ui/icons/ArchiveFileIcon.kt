package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ArchiveFileIcon: ImageVector
    get() {
        if (_ArchiveFileIcon != null) {
            return _ArchiveFileIcon!!
        }
        _ArchiveFileIcon = ImageVector.Builder(
            name = "ArchiveFileIcon",
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
                        0.5f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(20f, 5f),
                    end = Offset(26f, 34.5f)
                )
            ) {
                moveTo(27f, 33f)
                curveTo(27f, 34.105f, 26.105f, 35f, 25f, 35f)
                horizontalLineTo(23f)
                curveTo(21.895f, 35f, 21f, 34.105f, 21f, 33f)
                verticalLineTo(26f)
                horizontalLineTo(23f)
                verticalLineTo(24f)
                horizontalLineTo(25f)
                verticalLineTo(26f)
                horizontalLineTo(27f)
                verticalLineTo(33f)
                close()
                moveTo(24f, 23f)
                horizontalLineTo(21f)
                verticalLineTo(21f)
                horizontalLineTo(24f)
                verticalLineTo(23f)
                close()
                moveTo(27f, 21f)
                horizontalLineTo(24f)
                verticalLineTo(19f)
                horizontalLineTo(27f)
                verticalLineTo(21f)
                close()
                moveTo(24f, 19f)
                horizontalLineTo(21f)
                verticalLineTo(17f)
                horizontalLineTo(24f)
                verticalLineTo(19f)
                close()
                moveTo(27f, 17f)
                horizontalLineTo(24f)
                verticalLineTo(15f)
                horizontalLineTo(27f)
                verticalLineTo(17f)
                close()
                moveTo(24f, 15f)
                horizontalLineTo(21f)
                verticalLineTo(13f)
                horizontalLineTo(24f)
                verticalLineTo(15f)
                close()
                moveTo(27f, 13f)
                horizontalLineTo(24f)
                verticalLineTo(11f)
                horizontalLineTo(27f)
                verticalLineTo(13f)
                close()
                moveTo(24f, 11f)
                horizontalLineTo(21f)
                verticalLineTo(9f)
                horizontalLineTo(24f)
                verticalLineTo(11f)
                close()
                moveTo(27f, 9f)
                horizontalLineTo(24f)
                verticalLineTo(7f)
                horizontalLineTo(27f)
                verticalLineTo(9f)
                close()
                moveTo(24f, 7f)
                horizontalLineTo(21f)
                verticalLineTo(5f)
                horizontalLineTo(24f)
                verticalLineTo(7f)
                close()
            }
        }.build()

        return _ArchiveFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ArchiveFileIcon: ImageVector? = null
