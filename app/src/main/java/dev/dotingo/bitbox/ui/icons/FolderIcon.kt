package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FolderIcon: ImageVector
    get() {
        if (_FolderIcon != null) {
            return _FolderIcon!!
        }
        _FolderIcon = ImageVector.Builder(
            name = "FolderIcon",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        1f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(2.336f, 2.836f),
                    end = Offset(19.871f, 20.371f)
                )
            ) {
                moveTo(19f, 5f)
                horizontalLineToRelative(-8f)
                lineToRelative(-0.544f, -1.632f)
                curveTo(10.184f, 2.551f, 9.419f, 2f, 8.558f, 2f)
                horizontalLineTo(4f)
                curveTo(2.895f, 2f, 2f, 2.895f, 2f, 4f)
                verticalLineToRelative(13f)
                curveToRelative(0f, 1.657f, 1.343f, 3f, 3f, 3f)
                horizontalLineToRelative(14f)
                curveToRelative(1.657f, 0f, 3f, -1.343f, 3f, -3f)
                verticalLineTo(8f)
                curveTo(22f, 6.343f, 20.657f, 5f, 19f, 5f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        0.493f to Color.White.copy(alpha = 0f),
                        0.997f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(2.336f, 2.836f),
                    end = Offset(19.871f, 20.371f)
                )
            ) {
                moveTo(8.558f, 2.5f)
                curveToRelative(0.647f, 0f, 1.219f, 0.412f, 1.423f, 1.026f)
                lineToRelative(0.544f, 1.632f)
                lineTo(10.64f, 5.5f)
                horizontalLineTo(11f)
                horizontalLineToRelative(8f)
                curveToRelative(1.379f, 0f, 2.5f, 1.121f, 2.5f, 2.5f)
                verticalLineToRelative(9f)
                curveToRelative(0f, 1.379f, -1.121f, 2.5f, -2.5f, 2.5f)
                horizontalLineTo(5f)
                curveToRelative(-1.379f, 0f, -2.5f, -1.121f, -2.5f, -2.5f)
                verticalLineTo(4f)
                curveToRelative(0f, -0.827f, 0.673f, -1.5f, 1.5f, -1.5f)
                horizontalLineTo(8.558f)
                moveTo(8.558f, 2f)
                horizontalLineTo(4f)
                curveTo(2.895f, 2f, 2f, 2.895f, 2f, 4f)
                verticalLineToRelative(13f)
                curveToRelative(0f, 1.657f, 1.343f, 3f, 3f, 3f)
                horizontalLineToRelative(14f)
                curveToRelative(1.657f, 0f, 3f, -1.343f, 3f, -3f)
                verticalLineTo(8f)
                curveToRelative(0f, -1.657f, -1.343f, -3f, -3f, -3f)
                horizontalLineToRelative(-8f)
                lineToRelative(-0.544f, -1.632f)
                curveTo(10.184f, 2.551f, 9.419f, 2f, 8.558f, 2f)
                lineTo(8.558f, 2f)
                close()
            }
        }.build()

        return _FolderIcon!!
    }

@Suppress("ObjectPropertyName")
private var _FolderIcon: ImageVector? = null
