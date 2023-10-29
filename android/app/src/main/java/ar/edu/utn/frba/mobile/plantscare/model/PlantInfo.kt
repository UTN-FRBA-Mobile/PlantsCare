package ar.edu.utn.frba.mobile.plantscare.model

data class PlantInfo(
    val id: Int,

    // Pantalla 1
    val name: String,
    val image: String,
    val size: String,
    val environment: String,
    val sunExposure: String,
    val difficulty: String,
    val wateringFrequency: Int,
    val description: String,

    // Pantalla 2
    val imageGallery: List<String>,

    // Pantalla 4
    val wateringFrequencyByMonth: Map<String, Int>
    )