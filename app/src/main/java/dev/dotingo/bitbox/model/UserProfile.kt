package dev.dotingo.bitbox.model

import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("_id") val id: String,
    val login: String,
    val email: String,
    val createdAt: String,
    val avatar: String?,
    val role: String,
    @SerializedName("prefered_contacts") val preferredContacts: String?,
    val isCreator: Boolean
)
