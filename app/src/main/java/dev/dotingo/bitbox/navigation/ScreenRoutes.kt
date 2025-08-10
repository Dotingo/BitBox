package dev.dotingo.bitbox.navigation

import kotlinx.serialization.Serializable

@Serializable
object RegistrationScreenNav

@Serializable
object LoginScreenNav

@Serializable
object StorageScreenNav

@Serializable
object ProfileScreenNav

@Serializable
data class EntitiesScreenNav(val storageId: String, val storageName: String)