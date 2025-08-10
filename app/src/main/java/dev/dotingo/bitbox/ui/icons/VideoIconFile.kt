package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val VideoFileIcon: ImageVector
    get() {
        if (_VideoFileIcon != null) {
            return _VideoFileIcon!!
        }
        _VideoFileIcon = ImageVector.Builder(
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
                    start = Offset(15f, 21f),
                    end = Offset(26f, 36f)
                )
            ) {
                moveTo(33.05f, 23.43f)
                curveTo(32.755f, 23.221f, 32.414f, 23.085f, 32.057f, 23.031f)
                curveTo(31.699f, 22.977f, 31.334f, 23.007f, 30.99f, 23.12f)
                lineTo(27.91f, 24.12f)
                curveTo(27.71f, 23.233f, 27.214f, 22.441f, 26.503f, 21.873f)
                curveTo(25.792f, 21.306f, 24.909f, 20.998f, 24f, 21f)
                horizontalLineTo(18f)
                curveTo(16.939f, 21f, 15.922f, 21.421f, 15.172f, 22.172f)
                curveTo(14.421f, 22.922f, 14f, 23.939f, 14f, 25f)
                verticalLineTo(31f)
                curveTo(14f, 32.061f, 14.421f, 33.078f, 15.172f, 33.828f)
                curveTo(15.922f, 34.579f, 16.939f, 35f, 18f, 35f)
                horizontalLineTo(24f)
                curveTo(24.912f, 35.001f, 25.797f, 34.69f, 26.508f, 34.119f)
                curveTo(27.219f, 33.548f, 27.714f, 32.751f, 27.91f, 31.86f)
                lineTo(30.99f, 32.86f)
                curveTo(31.222f, 32.939f, 31.465f, 32.979f, 31.71f, 32.98f)
                curveTo(32.011f, 32.981f, 32.31f, 32.923f, 32.588f, 32.808f)
                curveTo(32.866f, 32.694f, 33.119f, 32.525f, 33.332f, 32.312f)
                curveTo(33.545f, 32.099f, 33.714f, 31.846f, 33.828f, 31.568f)
                curveTo(33.943f, 31.289f, 34.001f, 30.991f, 34f, 30.69f)
                verticalLineTo(25.29f)
                curveTo(34f, 24.927f, 33.915f, 24.569f, 33.749f, 24.245f)
                curveTo(33.584f, 23.922f, 33.345f, 23.642f, 33.05f, 23.43f)
                close()
                moveTo(23f, 27f)
                horizontalLineTo(21f)
                curveTo(20.735f, 27f, 20.48f, 26.895f, 20.293f, 26.707f)
                curveTo(20.105f, 26.52f, 20f, 26.265f, 20f, 26f)
                curveTo(20f, 25.735f, 20.105f, 25.48f, 20.293f, 25.293f)
                curveTo(20.48f, 25.105f, 20.735f, 25f, 21f, 25f)
                horizontalLineTo(23f)
                curveTo(23.265f, 25f, 23.52f, 25.105f, 23.707f, 25.293f)
                curveTo(23.895f, 25.48f, 24f, 25.735f, 24f, 26f)
                curveTo(24f, 26.265f, 23.895f, 26.52f, 23.707f, 26.707f)
                curveTo(23.52f, 26.895f, 23.265f, 27f, 23f, 27f)
                close()
            }
        }.build()

        return _VideoFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _VideoFileIcon: ImageVector? = null

