package dev.dotingo.bitbox.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class RegisterRequest(
    val login: String,
    val email: String,
    val password: String,
    val role: String
)
