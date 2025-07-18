package dev.dotingo.bitbox.model

data class Storage(
    val _id: String,
    val name: String,
    val description: String?,
    val owner: String,
    val used: Long,
    val size: Long,
    val access: String,
    val members: List<Member>,
    val restrictFileSize: Boolean,
    val maxFileSize: Int,
    val restrictFilesCount: Boolean,
    val maxFilesCount: Int,
    val defaultRole: String,
    val createdAt: String
)

data class StorageWithOwner(
    val storage: Storage,
    val ownerLogin: String?
)