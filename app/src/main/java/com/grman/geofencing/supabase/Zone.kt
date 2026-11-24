package com.grman.geofencing.supabase

import kotlinx.serialization.Serializable

@Serializable
data class Zone(
    val id: String,
    val name: String,
    val photosarray: List<String> = emptyList(),
    val created_at: String? = null
)