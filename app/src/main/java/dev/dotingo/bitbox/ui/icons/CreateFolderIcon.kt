package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CreateFolderIcon: ImageVector
    get() {
        if (_CreateFolderIcon != null) {
            return _CreateFolderIcon!!
        }
        _CreateFolderIcon = ImageVector.Builder(
            name = "CreateFolderIcon",
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
                    start = Offset(4.672f, 5.672f),
                    end = Offset(39.742f, 40.742f)
                )
            ) {
                moveTo(38f, 10f)
                horizontalLineTo(22f)
                lineTo(20.912f, 6.736f)
                curveTo(20.368f, 5.102f, 18.838f, 4f, 17.116f, 4f)
                horizontalLineTo(8f)
                curveTo(5.79f, 4f, 4f, 5.79f, 4f, 8f)
                verticalLineTo(34f)
                curveTo(4f, 37.314f, 6.686f, 40f, 10f, 40f)
                horizontalLineTo(38f)
                curveTo(41.314f, 40f, 44f, 37.314f, 44f, 34f)
                verticalLineTo(16f)
                curveTo(44f, 12.686f, 41.314f, 10f, 38f, 10f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        0.493f to Color.White.copy(alpha = 0f),
                        0.997f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(4.672f, 5.672f),
                    end = Offset(39.742f, 40.742f)
                )
            ) {
                moveTo(17.116f, 5f)
                curveTo(18.41f, 5f, 19.554f, 5.824f, 19.962f, 7.052f)
                lineTo(21.05f, 10.316f)
                lineTo(21.28f, 11f)
                horizontalLineTo(22f)
                horizontalLineTo(38f)
                curveTo(40.758f, 11f, 43f, 13.242f, 43f, 16f)
                verticalLineTo(34f)
                curveTo(43f, 36.758f, 40.758f, 39f, 38f, 39f)
                horizontalLineTo(10f)
                curveTo(7.242f, 39f, 5f, 36.758f, 5f, 34f)
                verticalLineTo(8f)
                curveTo(5f, 6.346f, 6.346f, 5f, 8f, 5f)
                horizontalLineTo(17.116f)
                close()
                moveTo(17.116f, 4f)
                horizontalLineTo(8f)
                curveTo(5.79f, 4f, 4f, 5.79f, 4f, 8f)
                verticalLineTo(34f)
                curveTo(4f, 37.314f, 6.686f, 40f, 10f, 40f)
                horizontalLineTo(38f)
                curveTo(41.314f, 40f, 44f, 37.314f, 44f, 34f)
                verticalLineTo(16f)
                curveTo(44f, 12.686f, 41.314f, 10f, 38f, 10f)
                horizontalLineTo(22f)
                lineTo(20.912f, 6.736f)
                curveTo(20.368f, 5.102f, 18.838f, 4f, 17.116f, 4f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.69803923f),
                        0.52f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(20.018f, 18.271f),
                    end = Offset(26.783f, 32.364f)
                )
            ) {
                moveTo(23.965f, 17.144f)
                curveTo(24.517f, 17.144f, 24.965f, 17.591f, 24.965f, 18.144f)
                verticalLineTo(24.036f)
                horizontalLineTo(30.856f)
                curveTo(31.409f, 24.036f, 31.856f, 24.484f, 31.856f, 25.036f)
                curveTo(31.856f, 25.588f, 31.409f, 26.036f, 30.856f, 26.036f)
                horizontalLineTo(24.965f)
                verticalLineTo(31.928f)
                curveTo(24.965f, 32.48f, 24.517f, 32.928f, 23.965f, 32.928f)
                curveTo(23.413f, 32.928f, 22.965f, 32.48f, 22.965f, 31.928f)
                verticalLineTo(26.036f)
                horizontalLineTo(17.072f)
                curveTo(16.52f, 26.036f, 16.073f, 25.588f, 16.072f, 25.036f)
                curveTo(16.072f, 24.484f, 16.52f, 24.036f, 17.072f, 24.036f)
                horizontalLineTo(22.965f)
                verticalLineTo(18.144f)
                curveTo(22.965f, 17.591f, 23.413f, 17.144f, 23.965f, 17.144f)
                close()
            }
        }.build()

        return _CreateFolderIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CreateFolderIcon: ImageVector? = null
