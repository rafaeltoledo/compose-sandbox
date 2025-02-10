package net.rafaeltoledo.sandbox.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection<T>(
  @SerialName("items") val items: List<T>,
  @SerialName("has_more") val hasMore: Boolean,
)

@Serializable
data class User(
  @SerialName("user_id") val id: Long,
  @SerialName("display_name") val displayName: String,
  @SerialName("profile_image") val profileImage: String,
  @SerialName("location") val location: String?,
  @SerialName("link") val link: String,
)
