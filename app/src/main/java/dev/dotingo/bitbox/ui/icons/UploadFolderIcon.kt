package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val UploadFolderIcon: ImageVector
    get() {
        if (_UploadFolderIcon != null) {
            return _UploadFolderIcon!!
        }
        _UploadFolderIcon = ImageVector.Builder(
            name = "UploadFolderIcon",
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
                        0.5f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(19f, 16.063f),
                    end = Offset(26.656f, 32.396f)
                )
            ) {
                moveTo(23f, 32f)
                curveTo(23f, 32.552f, 23.448f, 33f, 24f, 33f)
                curveTo(24.552f, 33f, 25f, 32.552f, 25f, 32f)
                horizontalLineTo(24f)
                horizontalLineTo(23f)
                close()
                moveTo(24.707f, 16.293f)
                curveTo(24.317f, 15.902f, 23.683f, 15.902f, 23.293f, 16.293f)
                lineTo(16.929f, 22.657f)
                curveTo(16.538f, 23.047f, 16.538f, 23.681f, 16.929f, 24.071f)
                curveTo(17.319f, 24.462f, 17.953f, 24.462f, 18.343f, 24.071f)
                lineTo(24f, 18.414f)
                lineTo(29.657f, 24.071f)
                curveTo(30.047f, 24.462f, 30.681f, 24.462f, 31.071f, 24.071f)
                curveTo(31.462f, 23.681f, 31.462f, 23.047f, 31.071f, 22.657f)
                lineTo(24.707f, 16.293f)
                close()
                moveTo(24f, 32f)
                horizontalLineTo(25f)
                lineTo(25f, 17f)
                horizontalLineTo(24f)
                horizontalLineTo(23f)
                lineTo(23f, 32f)
                horizontalLineTo(24f)
                close()
            }
        }.build()

        return _UploadFolderIcon!!
    }

@Suppress("ObjectPropertyName")
private var _UploadFolderIcon: ImageVector? = null
