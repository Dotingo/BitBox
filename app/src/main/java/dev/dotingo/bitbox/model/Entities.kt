package dev.dotingo.bitbox.model

data class Entities(
    val _id: String?,
    val name: String?,
    val extension: String?,
    val fullname: String?,
    val type: String?,
    val size: Long?,
    val storage: String?,
    val parent: String?,
    val uploadedAt: String?,
    val uploader: String?
)

data class EntitiesResponse(
    val items: List<Entities>
)
