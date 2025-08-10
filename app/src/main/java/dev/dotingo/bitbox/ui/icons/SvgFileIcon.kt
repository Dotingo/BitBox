package dev.dotingo.bitbox.ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val SvgFileIcon: ImageVector
    get() {
        if (_SvgFileIcon != null) {
            return _SvgFileIcon!!
        }
        _SvgFileIcon = ImageVector.Builder(
            name = "SvgFileIcon",
            defaultWidth = 48.dp,
            defaultHeight = 47.dp,
            viewportWidth = 48f,
            viewportHeight = 47f
        ).apply {
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        1f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(5.636f, 9.672f),
                    end = Offset(37.438f, 42.151f)
                )
            ) {
                moveTo(8f, 37.208f)
                verticalLineTo(9.792f)
                curveTo(8f, 6.547f, 10.686f, 3.917f, 14f, 3.917f)
                horizontalLineTo(28f)
                lineTo(40f, 15.667f)
                verticalLineTo(37.208f)
                curveTo(40f, 40.453f, 37.314f, 43.083f, 34f, 43.083f)
                horizontalLineTo(14f)
                curveTo(10.686f, 43.083f, 8f, 40.453f, 8f, 37.208f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.6f),
                        0.493f to Color.White.copy(alpha = 0f),
                        0.997f to Color.White.copy(alpha = 0.29803923f)
                    ),
                    start = Offset(5.636f, 9.672f),
                    end = Offset(37.438f, 42.151f)
                )
            ) {
                moveTo(27.586f, 4.896f)
                lineTo(39f, 16.072f)
                verticalLineTo(37.208f)
                curveTo(39f, 39.907f, 36.756f, 42.104f, 34f, 42.104f)
                horizontalLineTo(14f)
                curveTo(11.244f, 42.104f, 9f, 39.907f, 9f, 37.208f)
                verticalLineTo(9.792f)
                curveTo(9f, 7.091f, 11.244f, 4.896f, 14f, 4.896f)
                horizontalLineTo(27.586f)
                close()
                moveTo(28f, 3.917f)
                horizontalLineTo(14f)
                curveTo(10.686f, 3.917f, 8f, 6.547f, 8f, 9.792f)
                verticalLineTo(37.208f)
                curveTo(8f, 40.453f, 10.686f, 43.083f, 14f, 43.083f)
                horizontalLineTo(34f)
                curveTo(37.314f, 43.083f, 40f, 40.453f, 40f, 37.208f)
                verticalLineTo(15.667f)
                lineTo(28f, 3.917f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.69803923f),
                        0.519f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(25.586f, 6.28f),
                    end = Offset(37.333f, 18.278f)
                )
            ) {
                moveTo(28f, 11.75f)
                verticalLineTo(3.917f)
                lineTo(40f, 15.667f)
                horizontalLineTo(32f)
                curveTo(29.79f, 15.667f, 28f, 13.914f, 28f, 11.75f)
                close()
            }
            path(
                fill = Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color.White.copy(alpha = 0.69803923f),
                        0.519f to Color.White.copy(alpha = 0.44705883f),
                        1f to Color.White.copy(alpha = 0.54901963f)
                    ),
                    start = Offset(31.341f, 22.064f),
                    end = Offset(33.781f, 24.556f)
                )
            ) {
                moveTo(24.66f, 20.697f)
                curveTo(25.633f, 20.697f, 26.422f, 21.469f, 26.422f, 22.422f)
                horizontalLineTo(29.394f)
                curveTo(30.195f, 22.422f, 30.718f, 22.405f, 31.06f, 22.424f)
                curveTo(31.365f, 21.907f, 31.934f, 21.56f, 32.587f, 21.56f)
                curveTo(33.56f, 21.56f, 34.349f, 22.332f, 34.349f, 23.284f)
                curveTo(34.349f, 24.237f, 33.56f, 25.009f, 32.587f, 25.009f)
                curveTo(31.927f, 25.009f, 31.352f, 24.653f, 31.05f, 24.127f)
                curveTo(30.696f, 24.169f, 30.167f, 24.147f, 29.394f, 24.147f)
                horizontalLineTo(28.504f)
                curveTo(29.93f, 25.37f, 30.826f, 27.138f, 30.826f, 29.105f)
                curveTo(30.826f, 29.178f, 30.824f, 29.25f, 30.822f, 29.321f)
                horizontalLineTo(30.826f)
                curveTo(31.798f, 29.321f, 32.587f, 30.093f, 32.587f, 31.046f)
                verticalLineTo(32.771f)
                curveTo(32.587f, 33.723f, 31.798f, 34.495f, 30.826f, 34.495f)
                horizontalLineTo(29.064f)
                curveTo(28.091f, 34.495f, 27.303f, 33.723f, 27.303f, 32.771f)
                verticalLineTo(31.046f)
                curveTo(27.303f, 30.093f, 28.091f, 29.321f, 29.064f, 29.321f)
                horizontalLineTo(29.059f)
                curveTo(29.062f, 29.25f, 29.064f, 29.178f, 29.064f, 29.105f)
                curveTo(29.064f, 27.254f, 27.966f, 25.599f, 26.311f, 24.75f)
                curveTo(26.061f, 25.405f, 25.417f, 25.872f, 24.66f, 25.872f)
                horizontalLineTo(22.899f)
                curveTo(22.143f, 25.872f, 21.498f, 25.405f, 21.249f, 24.75f)
                curveTo(19.593f, 25.6f, 18.495f, 27.254f, 18.495f, 29.105f)
                curveTo(18.495f, 29.178f, 18.498f, 29.25f, 18.501f, 29.321f)
                horizontalLineTo(18.495f)
                curveTo(19.468f, 29.321f, 20.257f, 30.093f, 20.257f, 31.046f)
                verticalLineTo(32.771f)
                curveTo(20.257f, 33.723f, 19.468f, 34.495f, 18.495f, 34.495f)
                horizontalLineTo(16.734f)
                curveTo(15.761f, 34.495f, 14.972f, 33.723f, 14.972f, 32.771f)
                verticalLineTo(31.046f)
                curveTo(14.972f, 30.093f, 15.761f, 29.321f, 16.734f, 29.321f)
                horizontalLineTo(16.738f)
                curveTo(16.735f, 29.25f, 16.734f, 29.178f, 16.734f, 29.105f)
                curveTo(16.734f, 27.138f, 17.63f, 25.37f, 19.056f, 24.147f)
                horizontalLineTo(18.165f)
                curveTo(17.357f, 24.147f, 16.831f, 24.172f, 16.49f, 24.16f)
                curveTo(16.183f, 24.668f, 15.619f, 25.009f, 14.972f, 25.009f)
                curveTo(14f, 25.009f, 13.211f, 24.237f, 13.211f, 23.284f)
                curveTo(13.211f, 22.332f, 14f, 21.56f, 14.972f, 21.56f)
                curveTo(15.628f, 21.56f, 16.2f, 21.911f, 16.503f, 22.432f)
                curveTo(16.877f, 22.423f, 17.411f, 22.422f, 18.165f, 22.422f)
                horizontalLineTo(21.138f)
                curveTo(21.138f, 21.469f, 21.926f, 20.697f, 22.899f, 20.697f)
                horizontalLineTo(24.66f)
                close()
            }
        }.build()

        return _SvgFileIcon!!
    }

@Suppress("ObjectPropertyName")
private var _SvgFileIcon: ImageVector? = null
