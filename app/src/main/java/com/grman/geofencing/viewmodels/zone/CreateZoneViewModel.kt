package com.grman.geofencing.viewmodels.zone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateZoneViewModel : ViewModel() {

    // Emplacement
    var locationName by mutableStateOf("Campus de l'UQAC")

    var latitude by mutableStateOf("48.4201")

    var longitude by mutableStateOf("-71.0526")

    var radius by mutableIntStateOf(150)

    // Informations de la zone
    var selectedType by mutableStateOf("Autre")

    var zoneName by mutableStateOf("Campus de l'UQAC")

    var description by mutableStateOf(
        "Un point de rencontre sur le campus de l'UQAC. " +
                "Une nouvelle zone à découvrir à Chicoutimi.")

    var descriptionMaxLength by mutableIntStateOf(500)

    // Photos
    var photoCount by mutableIntStateOf(0)


    // Autres
    var isLoading by mutableStateOf(false)

    var errorMessage by mutableStateOf<String?>(null)

    // Type
    fun updateType(type: String) {
        selectedType = type
    }

    // Nom
    fun updateName(name: String) {
        zoneName = name
        errorMessage = null
    }

    // Description
    fun updateDescription(value: String) {
        if (value.length <= 500) {
            description = value
        }
        // todo: else -> error ? à voir
    }

    // Photos
    fun updatePhotoCount(count: Int) {
        photoCount = count.coerceIn(0, 3)
    }

    // Publish
    fun publish(onSuccess: () -> Unit) {
        if (zoneName.isBlank()) {
            errorMessage = "Le nom de la zone est obligatoire."
            return
        }

        if (description.isBlank()) {
            errorMessage = "La description est obligatoire."
            return
        }

        // todo: Supabase
        onSuccess()
    }
}