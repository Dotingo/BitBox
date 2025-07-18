package dev.dotingo.bitbox.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Donut() {
    val screenWidth = 50
    val screenHeight = 40

    var frame by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        var A = 0.0
        var B = 0.0
        while (true) {
            frame = renderDonutFrame(screenWidth, screenHeight, A, B)
            A += 0.04
            B += 0.02
            delay(50) // примерно 20 FPS
        }
    }

    Text(
        color = MaterialTheme.colorScheme.onBackground,
        text = frame,
        fontFamily = FontFamily.Monospace,
        fontSize = 8.sp,
        lineHeight = 8.sp
    )
}

fun renderDonutFrame(screenWidth: Int, screenHeight: Int, A: Double, B: Double): String {
    val thetaSpacing = 0.07
    val phiSpacing = 0.02

    val R1 = 0.5
    val R2 = 1.0
    val K2 = 2.0

    val K1 = screenWidth * K2 * 3.0 / (8.0 * (R1 + R2)) * 0.6

    val output = Array(screenWidth * screenHeight) { ' ' }
    val zBuffer = DoubleArray(screenWidth * screenHeight) { 0.0 }

    val cosA = cos(A)
    val sinA = sin(A)
    val cosB = cos(B)
    val sinB = sin(B)

    var theta = 0.0
    while (theta < 2 * Math.PI) {
        val costheta = cos(theta)
        val sintheta = sin(theta)

        var phi = 0.0
        while (phi < 2 * Math.PI) {
            val cosphi = cos(phi)
            val sinphi = sin(phi)

            val circlex = R2 + R1 * costheta
            val circley = R1 * sintheta

            val x = circlex * (cosB * cosphi + sinA * sinB * sinphi) - circley * cosA * sinB
            val y = circlex * (sinB * cosphi - sinA * cosB * sinphi) + circley * cosA * cosB
            val z = K2 + cosA * circlex * sinphi + circley * sinA
            val ooz = 1 / z

            val xp = (screenWidth / 2 + K1 * ooz * x).toInt()
            val yp = (screenHeight / 2 - K1 * ooz * y).toInt()

            val luminance = cosphi * costheta * sinB - cosA * costheta * sinphi - sinA * sintheta +
                    cosB * (cosA * sintheta - costheta * sinA * sinphi)

            if (luminance > 0 && xp in 0 until screenWidth && yp in 0 until screenHeight) {
                val idx = xp + screenWidth * yp
                if (ooz > zBuffer[idx]) {
                    zBuffer[idx] = ooz
                    val luminanceIndex = (luminance * 8).toInt().coerceIn(0, 11)
                    val chars = ".,-~:;=!*#$@"
                    output[idx] = chars[luminanceIndex]
                }
            }
            phi += phiSpacing
        }
        theta += thetaSpacing
    }

    // собрать кадр в текст
    return buildString {
        for (j in 0 until screenHeight) {
            for (i in 0 until screenWidth) {
                append(output[i + screenWidth * j])
            }
            append("\n")
        }
    }
}