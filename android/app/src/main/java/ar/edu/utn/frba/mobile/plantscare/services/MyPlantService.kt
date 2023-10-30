package ar.edu.utn.frba.mobile.plantscare.services

import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPlantService {
    @GET("plants/{id}")
    suspend fun getPlantById(@Path("id") id: Int): PlantInfo
}
/*
object MyPlantService {
//    private val client = OkHttpClient()
    private val defaultPlant = PlantInfo(1,
    "Pothos",
    "My Pothos",
    "Pothos is an evergreen plant with thick, waxy, green, heart-shaped leaves with splashes of yellow",
    2,
    PlantProperties(
        "small",
        "interior",
        "high",
        "easy"),

    listOf(
        "https://png.pngtree.com/png-clipart/20210329/ourlarge/pngtree-happy-plant-png-image_3141339.jpg",
        "https://c8.alamy.com/compes/kyndht/cartoon-flor-feliz-en-una-olla-kyndht.jpg",
        "https://thumbs.dreamstime.com/z/l%C3%ADnea-fina-satisfecha-hoja-icono-de-la-planta-del-emoji-emoticon-sonriente-ramita-verde-kawaii-feliz-cara-contenta-155114164.jpg",
        "https://cdn.pixabay.com/photo/2022/01/08/11/06/plant-6923699_1280.png"),
    mapOf(
        "January" to 1,
        "February" to 1,
        "March" to 1,
        "April" to 3,
        "May" to 3,
        "June" to 5,
        "July" to 7,
        "August" to 5,
        "September" to 5,
        "October" to 3,
        "November" to 3,
        "December" to 1),
    listOf( PlantWateringHistory(LocalDateTime.now(), WateringStatus.WATERED))
    )

    fun getPlantInfo(plantId: Int): PlantInfo {
//        val url = "https://mi-test.com/getPlant/$plantId"
//        val request = Request.Builder()
//            .url(url)
//            .build()
//        try {
//            val response = client.newCall(request).execute()
//            if (response.isSuccessful) {
//                val responseBody = response.body?.string()
//                val gson = Gson()
//                return gson.fromJson(responseBody, Plant::class.java)
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }

        return defaultPlant
    }
}

 */